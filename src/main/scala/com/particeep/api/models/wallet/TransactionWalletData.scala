package com.particeep.api.models.wallet

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.Currency
import com.particeep.api.models.enums.TransactionWalletOperation.TransactionWalletOperation
import com.particeep.api.models.enums.TransactionWalletStatus.TransactionWalletStatus
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import play.api.libs.json.Writes

case class TransactionWalletData(
    id:                 String,
    created_at:         Option[OffsetDateTime]             = None,
    debited_amount:     Option[Int]                        = None,
    credited_amount:    Option[Int]                        = None,
    fees:               Option[Int]                        = None,
    currency:           Option[Currency]                   = None,
    status:             Option[TransactionWalletStatus]    = None,
    tag:                Option[String]                     = None,
    debited_wallet_id:  Option[String]                     = None,
    credited_wallet_id: Option[String]                     = None,
    transaction_id:     Option[String]                     = None,
    operation:          Option[TransactionWalletOperation] = None,
    fundraise_id:       Option[String]                     = None,
    fundraise_name:     Option[String]                     = None
)

object TransactionWalletData {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Jsonx.formatCaseClass[TransactionWalletData]
}
