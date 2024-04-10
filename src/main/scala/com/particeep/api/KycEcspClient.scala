package com.particeep.api

import play.api.libs.json.{ JsNull, Json }

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.enums.KycEcspType
import com.particeep.api.models.kyc_ecsp._

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

  def findCurrentKyc(user_id: String, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit
    ec:                       ExecutionContext
  ): Future[Either[ErrorResult, KycEcsp]] = {
    ws.get[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout)
  }

  def create(user_id: String, kyc: KycEcspUpdate, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit
    ec:               ExecutionContext
  ): Future[Either[ErrorResult, KycEcsp]] = {
    ws.put[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout, Json.toJson(kyc))
  }

  def update(user_id: String, kyc: KycEcspUpdate, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit
    ec:               ExecutionContext
  ): Future[Either[ErrorResult, KycEcsp]] = {
    ws.post[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout, Json.toJson(kyc))
  }

  def validate(user_id: String, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit
    ec:                 ExecutionContext
  ): Future[Either[ErrorResult, KycEcsp]] = {
    ws.post[KycEcsp](s"$endPoint/$user_id/$kyc_type/validate", timeout, JsNull)
  }

  def delete(user_id: String, timeout: Long = defaultTimeOut)(implicit
    ec:               ExecutionContext
  ): Future[Either[ErrorResult, Seq[KycEcsp]]] = {
    ws.delete[Seq[KycEcsp]](s"$endPoint/$user_id", timeout)
  }
}
