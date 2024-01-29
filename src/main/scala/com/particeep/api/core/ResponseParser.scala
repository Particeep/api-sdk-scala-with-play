package com.particeep.api.core

import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.util.ByteString
import org.slf4j.LoggerFactory
import play.api.libs.json._
import play.api.libs.ws.{ StandaloneWSRequest, StandaloneWSResponse }

import scala.util.control.NonFatal
import com.particeep.api.models._
import play.api.http.{ HeaderNames, HttpEntity }

import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.ws.JsonBodyReadables._
import play.api.mvc.Result
import play.api.mvc.Results.Ok
import play.shaded.ahc.org.asynchttpclient.Response

trait ResponseParser {

  import com.particeep.api.models.Errors._

  private[this] final lazy val log = LoggerFactory.getLogger(this.getClass)

  def parse[A](response: StandaloneWSResponse)(implicit json_reads: Reads[A]): Either[ErrorResult, A] = {
    parse(response.body[JsValue], response.status)(json_reads)
  }

  def parse[A](response: Response)(implicit json_reads: Reads[A]): Either[ErrorResult, A] = {
    val json: JsValue = Json.parse(response.getResponseBody)
    parse(json, response.getStatusCode)(json_reads)
  }

  def parseStream(request: StandaloneWSRequest)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    val response = request.execute()
    response.map { r =>
      try {
        Future.successful(Left(validateStandardError(r.body[JsValue]).get))
      } catch {
        case NonFatal(_) => request.stream().map(r => Right(r.bodyAsSource.mapMaterializedValue(_ => NotUsed)))
      }
    }.flatMap(identity)
  }

  def parseDocumentStream(
    request: StandaloneWSRequest
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, Result]] = {
    request
      .stream()
      .map(response => if (response.status < 300) constructResultForDocument(response) else constructError(response))
  }

  private[this] def constructResultForDocument(
    response: StandaloneWSResponse
  ): Right[ErrorResult, Result] = {
    val length_of_document: Option[Long] = response.headers.get(HeaderNames.CONTENT_LENGTH) match {
      case Some(Seq(length)) => Option(length).flatMap(_.toLongOption)
      case _                 => None
    }

    // if length of document is known, browser will display progress bar.
    if (length_of_document.isDefined) {
      Right[ErrorResult, Result](
        Ok.sendEntity(
          HttpEntity.Streamed(response.bodyAsSource, length_of_document, Some(response.contentType))
        )
          .withHeaders(response.headers.collect({ case (key, Seq(value)) => key -> value }).toSeq: _*)
      )
    } else {
      Right[ErrorResult, Result](
        Ok.chunked(response.bodyAsSource)
          .withHeaders(response.headers.collect({ case (key, Seq(value)) => key -> value }).toSeq: _*)
          .as(response.contentType)
      )
    }
  }

  private[this] def constructError(response: StandaloneWSResponse): Left[ErrorResult, Result] = {
    val body = response.body[JsValue]
    validateStandardError(body)
      .map(Left[ErrorResult, Result])
      .getOrElse(Left[ErrorResult, Result](ParsingError(hasError = true, errors = List(JsString("error.standard_error.unknown_error"), body))))
  }

  private[this] def parse[A](json: JsValue, status: Int)(implicit json_reads: Reads[A]): Either[ErrorResult, A] = {
    try {
      val result: Either[ErrorResult, A] = validateStandardError(json)
        .orElse(validateParsingError(json))
        .map(err => Left(err))
        .getOrElse(parseResult(json)(json_reads))
      result
    } catch {
      case NonFatal(ex) => {
        val msg = s"""Error while parsing the response :
                     |status = ${status}
                     |body = ${json}
           """.stripMargin
        log.error(msg, ex)
        Left(ex2error(ex))
      }
    }
  }

  private[this] def parse[A](json: JsValue)(implicit json_reads: Reads[A]): Either[JsError, A] = {
    json.validate[A] match {
      case result: JsSuccess[A] => Right(result.get)
      case err: JsError         => Left(err)
    }
  }

  private[this] def parseResult[A](json: JsValue)(implicit json_reads: Reads[A]): Either[ErrorResult, A] = {
    parse(json)(json_reads) match {
      case Right(success) => Right(success)
      case Left(json_err) => {
        val err = Error(
          technicalCode = json_err.errors.mkString(System.lineSeparator),
          message = "unknown json error"
        )
        Left(Errors(true, List(err)))
      }
    }
  }

  private[this] def ex2error(ex: Throwable): Errors = {
    val err = Error(
      technicalCode = ex.toString(),
      message = ex.getMessage,
      stack = Some(ex.getStackTrace().mkString("", System.lineSeparator, System.lineSeparator))
    )
    Errors(
      hasError = true,
      errors = List(err)
    )
  }

  private[this] def validateStandardError(json: JsValue): Option[ErrorResult] = {
    json.validate[Errors] match {
      case result: JsSuccess[Errors] => Some(result.get)
      case _: JsError                => validateParsingError(json)
    }
  }

  private[this] def validateParsingError(json: JsValue): Option[ParsingError] = {
    json.validate[ParsingError] match {
      case result: JsSuccess[ParsingError] => Some(result.get)
      case _: JsError                      => None
    }
  }

}
