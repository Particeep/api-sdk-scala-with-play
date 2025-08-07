package com.particeep.api

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.unicia.ClientDataUnicia

trait UniciaCapability {
  self: WSClient =>

  def unicia(credentials: ApiCredential): UniciaClient = new UniciaClient(this, credentials)
}

class UniciaClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  implicit val creds: ApiCredential = credentials

  def search(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:             ExecutionContext
  ): Future[Either[ErrorResult, ClientDataUnicia]] = {
    ws.get[ClientDataUnicia](s"/unicia/data/search/$user_id", timeout)
  }
}
