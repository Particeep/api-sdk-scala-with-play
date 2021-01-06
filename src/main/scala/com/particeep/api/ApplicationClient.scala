package com.particeep.api

import com.particeep.api.core.{ WSClient, WithWS }
import com.particeep.api.models.ErrorResult

import scala.concurrent.{ ExecutionContext, Future }

trait ApplicationCapability {
  self: WSClient =>

  def application: ApplicationClient = new ApplicationClient(this)
}

class ApplicationClient(val ws: WSClient) extends WithWS {
  def ping(timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, String]] = {
    ws.getOutVersion[String](s"/ping", timeout)
  }
}
