package com.particeep.api.core

import play.api.libs.json._
import play.api.libs.ws.JsonBodyWritables._
import play.api.libs.ws._
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.{ FilePart, Part }
import play.shaded.ahc.org.asynchttpclient.{ AsyncHttpClient, BoundRequestBuilder }

import java.io.File
import scala.concurrent.duration._
import scala.concurrent.{ ExecutionContext, Future }
import scala.util.control.NonFatal
import scala.util.{ Failure, Random, Success, Try }

import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.{ Flow, Keep, Sink, Source }
import org.apache.pekko.util.ByteString

import com.particeep.api.models.document.{ DocumentDownload, TimeBoundedUrls }
import com.particeep.api.models.{ Error, ErrorResult, Errors, ParsingError }

case class ApiCredential(apiKey: String, apiSecret: String, http_headers: Option[Seq[(String, String)]] = None) {
  def withHeader(name: String, value: String): ApiCredential = {
    val new_value = (name, value) :: this.http_headers.map(_.toList).getOrElse(List())
    this.copy(http_headers = Some(new_value))
  }
}

trait WSClient {
  val defaultTimeOut: Long
  val defaultImportTimeOut: Long

  def cleanup(): Unit
  def credentials: Option[ApiCredential]

  /**
   * @param path : relative path for the request
   * @param timeOut : Sets the maximum time in milliseconds you expect the request to take. default : infinite
   * @param exec : Execution context for the request
   * @return
   */
  def get[T](
    path:          String,
    timeOut:       Long,
    params:        List[(String, String)] = List()
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]]

  def post[T](
    path:          String,
    timeOut:       Long,
    body:          JsValue,
    params:        List[(String, String)] = List()
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]]

  def put[T](
    path:          String,
    timeOut:       Long,
    body:          JsValue
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]]

  def delete[T](
    path:          String,
    timeOut:       Long,
    body:          JsValue = Json.toJson(""),
    params:        List[(String, String)] = List()
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]]

  def postFile[T](
    path:          String,
    timeOut:       Long,
    file:          File,
    content_type:  String,
    bodyParts:     List[Part]
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]]

  def getStream(
    path:        String,
    timeOut:     Long,
    params:      List[(String, String)] = List()
  )(implicit
    exec:        ExecutionContext,
    credentials: ApiCredential
  ): Future[Either[ErrorResult, Source[ByteString, NotUsed]]]

  def getDoc(
    document_id:   String,
    timeOut:       Long
  )(implicit exec: ExecutionContext, credentials: ApiCredential): Future[Either[ErrorResult, DocumentDownload]]

  def getZip(
    seq_id:        Seq[String],
    timeOut:       Long
  )(implicit exec: ExecutionContext, credentials: ApiCredential): Future[Either[ErrorResult, DocumentDownload]]

  def postStream(
    path:        String,
    timeOut:     Long,
    body:        JsValue,
    params:      List[(String, String)] = List()
  )(implicit
    exec:        ExecutionContext,
    credentials: ApiCredential
  ): Future[Either[ErrorResult, Source[ByteString, NotUsed]]]

  def generateTimeBoundedUrls(
    path:          String,
    timeOut:       Long,
    documentsIds:  List[String]
  )(implicit exec: ExecutionContext, credentials: ApiCredential): Future[Either[ErrorResult, TimeBoundedUrls]]
}

trait BaseClient {
  self: WSClient =>

  // TODO: allow better config of WS
  // For more fine grain on config see https://www.playframework.com/documentation/2.4.x/ScalaWS
  //    val config = new NingAsyncHttpClientConfigBuilder(DefaultWSClientConfig()).build
  //    val builder = new AsyncHttpClientConfig.Builder(config)
  //    val client = new NingWSClient(builder.build)
  protected implicit val system: ActorSystem
  protected implicit val materializer: Materializer
  protected implicit val sslClient: StandaloneAhcWSClient = ApiClient.defaultSslClient(materializer)

  def cleanup(): Unit = {
    sslClient.close()
  }
}

/**
 * usage
 * val ws = new ApiClient(
 *   "https://api.particeep.com",
 *   "1",
 *   creds
 * ) with UserCapability
 *
 * val result:Future[Either[JsError, Info]] = ws.user.byId("some_id")
 */
class ApiClient(
  val baseUrl:         String,
  val version:         String,
  val credentials:     Option[ApiCredential] = None
)(implicit val system: ActorSystem, val materializer: Materializer) extends WSClient with BaseClient with Security
    with ResponseParser {

  val defaultTimeOut: Long       = 10000
  val defaultImportTimeOut: Long = -1

  private[this] def url(path: String, timeOut: Long)(implicit credentials: ApiCredential): StandaloneWSRequest = {
    val req = sslClient.url(s"$baseUrl/v$version$path")
    secure(req, credentials, timeOut).addHttpHeaders(credentials.http_headers.getOrElse(List()): _*)
  }

  private[this] def urlFileUpload(path: String, client: AsyncHttpClient, timeOut: Long)(implicit
    credentials:                        ApiCredential
  ): BoundRequestBuilder = {
    val postBuilder = client.preparePost(s"$baseUrl/v$version$path")
    val url         = secure(postBuilder, credentials, timeOut)
    credentials.http_headers.map(_.foldLeft(url) { (acc, elem) =>
      acc.addHeader(elem._1, elem._2)
    }).getOrElse(url)
  }

  def get[T](path: String, timeOut: Long, params: List[(String, String)] = List())(implicit
    exec:          ExecutionContext,
    credentials:   ApiCredential,
    f:             Format[T]
  ): Future[Either[ErrorResult, T]] = {
    url(path, timeOut).withQueryStringParameters(params: _*).get().map(parse[T](_)).recover {
      case NonFatal(e) => handle_error(e, "GET", path)
    }
  }

  def post[T](
    path:          String,
    timeOut:       Long,
    body:          JsValue,
    params:        List[(String, String)] = List()
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]] = {
    url(path, timeOut).withQueryStringParameters(params: _*).post(body).map(parse[T](_)).recover {
      case NonFatal(e) => handle_error(e, "POST", path)
    }
  }

  def put[T](path: String, timeOut: Long, body: JsValue)(implicit
    exec:          ExecutionContext,
    credentials:   ApiCredential,
    f:             Format[T]
  ): Future[Either[ErrorResult, T]] = {
    url(path, timeOut).put(body).map(parse[T](_)).recover {
      case NonFatal(e) => handle_error(e, "PUT", path)
    }
  }

  def delete[T](
    path:          String,
    timeOut:       Long,
    body:          JsValue = Json.toJson(""),
    params:        List[(String, String)] = List()
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]] = {
    url(path, timeOut).withQueryStringParameters(params: _*).withMethod("DELETE").withBody(body).execute().map(parse[T](
      _
    )).recover {
      case NonFatal(e) => handle_error(e, "DELETE", path)
    }
  }

  def postFile[T](
    path:          String,
    timeout:       Long,
    file:          File,
    contentType:   String,
    bodyParts:     List[Part]
  )(implicit exec: ExecutionContext, credentials: ApiCredential, f: Format[T]): Future[Either[ErrorResult, T]] = {
    val client      = sslClient.underlying[AsyncHttpClient]
    val postBuilder = urlFileUpload(path, client, timeout)
    val builder     = postBuilder.addBodyPart(
      new FilePart("document", file, contentType)
    )
    bodyParts.map(builder.addBodyPart)
    Future { client.executeRequest(builder.build()).get }.map(parse[T](_)).recover {
      case NonFatal(e) => handle_error(e, "DELETE", path)
    }
  }

  def getStream(
    path:        String,
    timeOut:     Long,
    params:      List[(String, String)] = List()
  )(implicit
    exec:        ExecutionContext,
    credentials: ApiCredential
  ): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    parseStream(url(path, timeOut).withQueryStringParameters(params: _*).withMethod("GET")).recover {
      case NonFatal(e) => handle_error[Source[ByteString, NotUsed]](e, "GET", path)
    }
  }

  def getDoc(
    document_id:   String,
    timeOut:       Long
  )(implicit exec: ExecutionContext, credentials: ApiCredential): Future[Either[ErrorResult, DocumentDownload]] = {
    getDocFromPath(s"$baseUrl/document/$document_id", timeOut)
  }

  def getZip(
    seq_id:        Seq[String],
    timeOut:       Long
  )(implicit exec: ExecutionContext, credentials: ApiCredential): Future[Either[ErrorResult, DocumentDownload]] = {
    getDocFromPath(s"$baseUrl/v$version/document/zip-download?ids=${seq_id.mkString(",")}", timeOut)
  }

  private[this] def getDocFromPath(
    path:          String,
    timeOut:       Long
  )(implicit exec: ExecutionContext, credentials: ApiCredential): Future[Either[ErrorResult, DocumentDownload]] = {
    secure(sslClient.url(path), credentials, timeOut)
      .addHttpHeaders(credentials.http_headers.getOrElse(List()): _*)
      .withRequestTimeout(timeOut millis)
      .withMethod(method = "GET")
      .stream()
      .flatMap(handleResponseForGetDoc)
      .recover {
        case NonFatal(e) => handle_error[DocumentDownload](e, method = "GET", path)
      }
  }

  private[this] def handleResponseForGetDoc(
    response: StandaloneWSRequest#Response
  ): Future[Either[ErrorResult, DocumentDownload]] = {
    if(response.status < 300) {
      Future.successful(
        Right(DocumentDownload(body = response.bodyAsSource, headers = response.headers))
      )
    } else {
      val source = response.bodyAsSource
      val flow   = Flow[ByteString]
        .reduce(_ ++ _)
        .map(_.utf8String)
        .map(convertStringToJson)
        .map(manageError)

      val default_error = Left[ErrorResult, DocumentDownload](ParsingError(
        hasError = true,
        errors   = List(JsString("body response is not a JSON"))
      ))

      val sink = Sink.fold[Left[ErrorResult, DocumentDownload], Left[ErrorResult, DocumentDownload]](default_error) {
        case (_, u) => u
      }

      val stream = source.via(flow).toMat(sink)(Keep.right)

      StreamHelper.with_default_supervision(stream).run()
    }
  }

  private[this] def convertStringToJson(data: String): JsValue = {
    Try {
      Json.parse(data)
    } match {
      case Failure(_)     => JsString(data)
      case Success(value) => value
    }
  }

  private[this] def manageError(json: JsValue): Left[ErrorResult, DocumentDownload] = {
    validateStandardError(json)
      .map(Left[ErrorResult, DocumentDownload])
      .getOrElse(Left[ErrorResult, DocumentDownload](ParsingError(
        hasError = true,
        errors   = List(JsString("error.standard_error.unknown_error"), json)
      )))
  }

  def postStream(
    path:        String,
    timeOut:     Long,
    body:        JsValue,
    params:      List[(String, String)] = List()
  )(implicit
    exec:        ExecutionContext,
    credentials: ApiCredential
  ): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    parseStream(url(path, timeOut).withQueryStringParameters(params: _*).withMethod("POST").withBody(body)).recover {
      case NonFatal(e) => handle_error[Source[ByteString, NotUsed]](e, "POST", path)
    }
  }

  def generateTimeBoundedUrls(
    path:          String,
    timeOut:       Long,
    documentsIds:  List[String]
  )(implicit exec: ExecutionContext, credentials: ApiCredential): Future[Either[ErrorResult, TimeBoundedUrls]] = {
    url(path, timeOut)
      .withQueryStringParameters(parameters = "ids" -> documentsIds.mkString(","))
      .withMethod(method = "GET")
      .get()
      .map(parse[TimeBoundedUrls])
      .recover {
        case NonFatal(e) => handle_error[TimeBoundedUrls](e, "GET", path)
      }
  }

  private[this] def handle_error[T](e: Throwable, method: String, path: String): Either[ErrorResult, T] = {
    val technical_code = "error.api.no.response"
    val error_msg      = s"ApiClient error for $method method on path $path"
    val error_id       = "#" + Random.alphanumeric.take(8).mkString
    Left(Errors(true, List(Error(technical_code, error_msg, Some(error_id), Some(e.getMessage)))))
  }
}

object ApiClient {

  def defaultSslClient(implicit m: Materializer): StandaloneAhcWSClient = StandaloneAhcWSClient()
}
