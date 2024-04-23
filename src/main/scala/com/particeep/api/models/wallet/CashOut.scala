package com.particeep.api.models.wallet

import play.api.libs.json.{ Json, OFormat }

case class CashOut(
  bank_account_id: Option[String] = None,
  amount:          Int            = 0,
  fees:            Option[Int]    = None,
  owner_ip:        Option[String] = None,
  tag:             Option[String] = None
)

object CashOut {
  val format: OFormat[CashOut] = Json.format[CashOut]
}
