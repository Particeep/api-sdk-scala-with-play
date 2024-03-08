package com.particeep.api.models.wallet

import play.api.libs.json.{Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class TransactionWallet(
    id:                 String                 = "",
    created_at:         Option[OffsetDateTime] = None,
    debited_wallet_id:  Option[String]         = None,
    credited_wallet_id: Option[String]         = None,
    debited_amount:     Option[Int]            = None,
    credited_amount:    Option[Int]            = None,
    fees:               Option[Int]            = None,
    currency:           Option[String]         = None,
    status:             Option[String]         = None,
    tag:                Option[String]         = None
)

object TransactionWallet {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[TransactionWallet]
}
