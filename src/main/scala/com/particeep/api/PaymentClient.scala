package com.particeep.api

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.payment.{ PayCancelledSchedulePaymentForParentAndDate, PayResult, PaymentCbCreation }
import com.particeep.api.models.transaction.Transaction
import play.api.libs.json.Json

import scala.concurrent.{ ExecutionContext, Future }

trait PaymentCapability {
  self: WSClient =>

  val payment: PaymentClient = new PaymentClient(this)
  def payment(credentials: ApiCredential): PaymentClient = new PaymentClient(this, Some(credentials))
}

object PaymentClient {

  private val endPoint: String = "/payment"
  private implicit lazy val pay_result_format = PayResult.format
  private implicit lazy val payment_cb_creation_format = PaymentCbCreation.format
  private implicit lazy val transaction_format = Transaction.format
  private implicit lazy val pay_cancelled_scheduled_payments_format = PayCancelledSchedulePaymentForParentAndDate.format
}

class PaymentClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {

  import PaymentClient._

  def payment(transaction_id: String, payment_cb_creation: PaymentCbCreation, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, PayResult]] = {
    ws.post[PayResult](s"$endPoint/$transaction_id", timeout, Json.toJson(payment_cb_creation))
  }

  def offlinePayment(transaction_id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, PayResult]] = {
    ws.post[PayResult](s"$endPoint/offline/$transaction_id", timeout, Json.toJson(""))
  }

  def walletPayment(transaction_id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, PayResult]] = {
    ws.post[PayResult](s"$endPoint/wallet/$transaction_id", timeout, Json.toJson(""))
  }

  def refund(transaction_id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/refund/$transaction_id", timeout, Json.toJson(""))
  }

  def payCancelledScheduledPayments(body: PayCancelledSchedulePaymentForParentAndDate, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, String]] = {
    ws.post[String](s"$endPoint/schedule/pay", timeout, Json.toJson(body))
  }
}
