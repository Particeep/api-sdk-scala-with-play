package com.particeep.api

import com.particeep.api.core.{ ApiCredential, EntityClient, WSClient, WithCredentials, WithWS }
import com.particeep.api.models.ErrorResult

import scala.concurrent.{ ExecutionContext, Future }

trait ApplicationCapability {
  self: WSClient =>

  val application: ApplicationClient = new ApplicationClient(this)

  def application(credentials: ApiCredential): ApplicationClient = new ApplicationClient(this, Some(credentials))
}

class ApplicationClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {
  def ping(timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, String]] = {
    ws.getOutVersion[String](s"/ping", timeout)
  }
}
