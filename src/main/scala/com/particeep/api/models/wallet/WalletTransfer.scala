package com.particeep.api.models.wallet

import play.api.libs.json.{Json, OFormat}

case class WalletTransfer(
  debited_wallet_id:  String         = "",
  credited_wallet_id: String         = "",
  amount:             Int            = 0,
  fees:               Option[Int]    = None,
  owner_ip:           Option[String] = None,
  tag:                Option[String] = None
)

object WalletTransfer {
  val format: OFormat[WalletTransfer] = Json.format[WalletTransfer]
}
