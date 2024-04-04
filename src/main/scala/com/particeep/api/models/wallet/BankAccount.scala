package com.particeep.api.models.wallet

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.BankAccountStatus.BankAccountStatus
import play.api.libs.json.{ Format, Json, Writes }

case class BankAccount(
    id:                        String                    = "",
    created_at:                Option[OffsetDateTime]    = None,
    wallet_id:                 Option[String]            = None,
    status:                    Option[BankAccountStatus] = None,
    bank_name:                 Option[String]            = None,
    iban:                      String                    = "",
    bic:                       Option[String]            = None,
    holder_name:               Option[String]            = None,
    acct_num:                  Option[String]            = None,
    aba_num:                   Option[String]            = None,
    transit_num:               Option[String]            = None,
    justificatory_document_id: Option[String]            = None,
    address:                   Option[Address]           = None
)

object BankAccount {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address] = Address.format
  val format = Json.format[BankAccount]
}
