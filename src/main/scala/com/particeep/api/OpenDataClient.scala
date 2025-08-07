package com.particeep.api

import play.api.libs.json.{ JsNull, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.partner.PartnerCompany
import com.particeep.api.models.user.User

trait OpenDataCapability {
  self: WSClient =>

  def open_data(credentials: ApiCredential): OpenDataClient = new OpenDataClient(this, credentials)
}

object OpenDataClient {

  private val endPoint: String = "/open-data"

  private implicit val user_format: OFormat[User]                      = User.format
  private implicit val partner_company_format: OFormat[PartnerCompany] = PartnerCompany.format
}

class OpenDataClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import OpenDataClient._

  implicit val creds: ApiCredential = credentials

  def updateRcsReport(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                      ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.post[User](s"$endPoint/rcs-report/$user_id", timeout, JsNull)
  }

  def updateOriasStatus(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                        ExecutionContext
  ): Future[Either[ErrorResult, PartnerCompany]] = {
    ws.post[PartnerCompany](s"$endPoint/orias-status/$user_id", timeout, JsNull)
  }
}
