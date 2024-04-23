package com.particeep.api.models.payment

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.transaction.Transaction
import com.particeep.api.models.wallet.CashInBankAccount

case class PayResult(
  transaction:  Transaction,
  payment_url:  Option[String]            = None,
  bank_account: Option[CashInBankAccount] = None
)

object PayResult {
  implicit val transaction_format: OFormat[Transaction]        = Transaction.format
  implicit val bank_account_format: OFormat[CashInBankAccount] = CashInBankAccount.format
  val format: OFormat[PayResult]                               = Json.format[PayResult]
}
