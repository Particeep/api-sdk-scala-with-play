package com.particeep.api

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.imports._
import play.api.libs.json._

import scala.concurrent.{ ExecutionContext, Future }

trait ImportCapability {
  self: WSClient =>

  val import_client: ImportClient = new ImportClient(this)
  def import_client(credentials: ApiCredential): ImportClient = new ImportClient(this, Some(credentials))
}

object ImportClient {
  private val endPoint: String = "/import"
  private implicit def format[T](implicit fmt: Format[T]) = ImportResult.format
}

class ImportClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {

  import ImportClient._

  def stateById[T](import_id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext, fmt: Format[T]): Future[Either[ErrorResult, ImportResult[T]]] = {
    ws.get[ImportResult[T]](s"$endPoint/state/$import_id", timeout)
  }
}
