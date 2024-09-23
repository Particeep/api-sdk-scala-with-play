package com.particeep.api.models.wallet

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency
import com.particeep.api.models.enums.WalletStatus.WalletStatus
import com.particeep.api.models.enums.WalletType.{ NATURAL, WalletType }

case class Wallet(
  id:               String                 = "",
  created_at:       Option[OffsetDateTime] = None,
  update_at:        Option[OffsetDateTime] = None,
  owner_id:         String                 = "",
  owner_type:       String                 = "",
  wallet_type:      WalletType             = NATURAL,
  wallet_target_id: Option[String]         = None,
  status:           Option[WalletStatus]   = None,
  currency:         Option[Currency]       = None,
  company_name:     Option[String]         = None,
  amount:           Int                    = 0
)

object Wallet {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[Wallet]                      = Json.format[Wallet]
}
