package com.particeep.api

import com.particeep.api.models.kyc_ecsp._
import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.json.{ Json, JsNull }
import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.enums.KycEcspType

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
    println(Console.MAGENTA + "findCurrentKyc " + kyc_type)
    kyc_type match {
      case KycEcspType.LEGAL => doFindL(user_id, kyc_type, timeout)
      case KycEcspType.NATURAL => doFindN(user_id, kyc_type, timeout)
    }
  }

  private[this] def doFindL(user_id: String, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp.Legal]] = {
    println(Console.MAGENTA + "doFind Legal")
    ws.get[KycEcsp.Legal](s"$endPoint/$user_id/$kyc_type", timeout)
  }

  private[this] def doFindN(user_id: String, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp.Natural]] = {
    println(Console.MAGENTA + "doFind Natural")
    ws.get[KycEcsp.Natural](s"$endPoint/$user_id/$kyc_type", timeout)
  }

  def create(user_id: String, kyc: KycEcspUpdate, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp]] = {
    ws.put[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout, Json.toJson(kyc))
  }

  def update(user_id: String, kyc: KycEcspUpdate, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp]] = {
    ws.post[KycEcsp](s"$endPoint/$user_id/$kyc_type", timeout, Json.toJson(kyc))
  }

  def validate(user_id: String, kyc_type: KycEcspType, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, KycEcsp]] = {
    ws.post[KycEcsp](s"$endPoint/$user_id/$kyc_type/validate", timeout, JsNull)
  }

  def delete(user_id: String, timeout: Long = defaultTimeOut)(implicit ec: ExecutionContext): Future[Either[ErrorResult, Seq[KycEcsp]]] = {
    ws.delete[Seq[KycEcsp]](s"$endPoint/$user_id", timeout)
  }

}