package com.particeep.api.models.wallet

import play.api.libs.json.{ Json, OFormat }

case class CashIn(
  amount:      Int            = 0,
  fees:        Option[Int]    = None,
  tag:         Option[String] = None,
  accept_url:  String         = "",
  decline_url: String         = "",
  pending_url: String         = "",
  owner_ip:    String         = "",
  locale:      Option[String] = None
)

object CashIn {
  val format: OFormat[CashIn] = Json.format[CashIn]
}
