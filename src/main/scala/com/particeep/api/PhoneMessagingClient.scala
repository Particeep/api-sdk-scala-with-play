package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.phonemessaging.{ PhoneMessage, SmsInformation }

trait PhoneMessagingCapability {
  self: WSClient =>

  val phone_messaging: PhoneMessagingClient                             = new PhoneMessagingClient(this)
  def phone_messaging(credentials: ApiCredential): PhoneMessagingClient =
    new PhoneMessagingClient(this, Some(credentials))
}

object PhoneMessagingClient {

  private val endPoint: String                                         = "/messaging"
  private implicit val format: OFormat[PhoneMessage]                   = PhoneMessage.format
  private implicit val sms_information_format: OFormat[SmsInformation] = SmsInformation.format
}

class PhoneMessagingClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import PhoneMessagingClient._

  def sendSms(sms_information: SmsInformation, timeout: Long = 5000)(implicit
    exec:                      ExecutionContext
  ): Future[Either[ErrorResult, PhoneMessage]] = {
    ws.post[PhoneMessage](s"$endPoint", timeout, Json.toJson(sms_information))
  }

  def byId(id: String, timeout: Long = 5000)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, PhoneMessage]] = {
    ws.get[PhoneMessage](s"$endPoint/$id", timeout)
  }

  def byIds(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[PhoneMessage]]] = {
    ws.get[List[PhoneMessage]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }
}
