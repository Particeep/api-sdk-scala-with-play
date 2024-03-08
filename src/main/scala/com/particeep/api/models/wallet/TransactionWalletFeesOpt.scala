package com.particeep.api.models.wallet

import play.api.libs.json.{ Json, OFormat }

case class TransactionWalletFeesOpt(
  transaction_wallet:          TransactionWallet         = TransactionWallet(),
  transaction_wallet_fees_opt: Option[TransactionWallet] = None
)

object TransactionWalletFeesOpt {
  implicit val transaction_wallet_format: OFormat[TransactionWallet] = TransactionWallet.format
  val format                                                         = Json.format[TransactionWalletFeesOpt]
}
