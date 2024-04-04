package com.particeep.api

import com.particeep.api.models.kyc_ecsp._

import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.json.{ JsNull, Json }
import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.enums.KycEcspType
import com.particeep.api.models.kyc_ecsp.KycEcspParser

trait KycEcspCapability {
  self: WSClient =>

  val kyc_ecsp: KycEcspClient = new KycEcspClient(this)

  def kyc_ecsp(credentials: ApiCredential): KycEcspClient = new KycEcspClient(this, Some(credentials))
}

object KycEcspClient {
  private val endPoint: String = "/kyc-ecsp"
}

class KycEcspClient(val ws: WSClient, val credentials: Option[ApiCredential] = None)
  extends WithWS
  with WithCredentials
  with EntityClient {

  import KycEcspClient._

  def findCurrentKyc(user_id: String, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp]] = {
    ws.get[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout)(ec, creds, KycEcspParser.format(kyc_type))
  }

  def create(user_id: String, kyc: KycEcspUpdate, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp]] = {
    ws.put[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout, Json.toJson(kyc))(ec, creds, KycEcspParser.format(kyc_type))
  }

  def update(user_id: String, kyc: KycEcspUpdate, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp]] = {
    ws.post[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout, Json.toJson(kyc))(ec, creds, KycEcspParser.format(kyc_type))
  }

  def validate(user_id: String, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp]] = {
    ws.post[KycEcsp](s"$endPoint/$user_id/$kyc_type/validate", timeout, JsNull)(ec, creds, KycEcspParser.format(kyc_type))
  }

  def delete(user_id: String, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcspCollection]] = {
    ws.delete[KycEcspCollection](s"$endPoint/$user_id", timeout)
  }
}
