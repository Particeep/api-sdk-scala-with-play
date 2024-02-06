package com.particeep.api.core

import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.util.ByteString
import org.slf4j.LoggerFactory
import play.api.libs.json._
import play.api.libs.ws.{ StandaloneWSRequest, StandaloneWSResponse }

import scala.util.control.NonFatal
import com.particeep.api.models._

import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.ws.JsonBodyReadables._
import play.shaded.ahc.org.asynchttpclient.Response
import com.particeep.api.models.document.DocumentDownload

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
    document_id: String,
    request:     StandaloneWSRequest
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, DocumentDownload]] = {
    request
      .stream()
      .map(response => if (response.status < 300) constructResultForDocument(document_id, response) else constructError(response))
  }

  private[this] def constructResultForDocument(
    document_id: String,
    response:    StandaloneWSResponse
  ): Right[ErrorResult, DocumentDownload] = {
    Right[ErrorResult, DocumentDownload](
      DocumentDownload(
        id = document_id,
        body = response.bodyAsSource,
        headers = response.headers
      )
    )
  }

  private[this] def constructError(response: StandaloneWSResponse): Left[ErrorResult, DocumentDownload] = {
    val body = response.body[JsValue]
    validateStandardError(body)
      .map(Left[ErrorResult, DocumentDownload])
      .getOrElse(Left[ErrorResult, DocumentDownload](ParsingError(hasError = true, errors = List(JsString("error.standard_error.unknown_error"), body))))
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
