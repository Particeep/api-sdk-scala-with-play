package com.particeep.api

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import play.api.libs.json.JsValue

import scala.concurrent.{ ExecutionContext, Future }

trait OpenDataCapability {
  self: WSClient =>

  val open_data: OpenDataClient = new OpenDataClient(this)

  def open_data(credentials: ApiCredential): OpenDataClient = new OpenDataClient(this, Some(credentials))
}

object OpenDataClient {

  private val endPoint: String = "/open-data"
}

class OpenDataClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {

  import OpenDataClient._

  def graphql(body: JsValue, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, JsValue]] = {
    ws.post[JsValue](s"$endPoint/graphql", timeout, body)
  }
}

