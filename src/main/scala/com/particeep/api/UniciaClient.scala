package com.particeep.api

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.unicia.ClientDataUnicia

import scala.concurrent.{ExecutionContext, Future}


trait UniciaCapability {
  self: WSClient =>

  val unicia: UniciaClient = new UniciaClient(this)

  def unicia(credentials: ApiCredential): UniciaClient = new UniciaClient(this, Some(credentials))
}

class UniciaClient(val ws: WSClient, val credentials: Option[ApiCredential] = None)
  extends WithWS
    with WithCredentials
    with EntityClient {

  def search(user_id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, ClientDataUnicia]] = {
    ws.get[ClientDataUnicia](s"/unicia/data/search/$user_id", timeout)
  }
}
