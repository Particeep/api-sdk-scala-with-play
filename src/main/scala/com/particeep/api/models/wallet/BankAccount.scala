package com.particeep.api.models.wallet

import play.api.libs.json.{Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.BankAccountStatus.BankAccountStatus

case class BankAccount(
    id:                     String                    = "",
    created_at:             Option[OffsetDateTime]    = None,
    wallet_id:              Option[String]            = None,
    status:                 Option[BankAccountStatus] = None,
    bank_name:              String                    = "",
    iban:                   String                    = "",
    bic:                    Option[String]            = None,
    holder_name:            Option[String]            = None,
    acct_num:               Option[String]            = None,
    aba_num:                Option[String]            = None,
    transit_num:            Option[String]            = None,
    account_type:           Option[String]            = None,
    justificatory_document: Option[String]            = None,
    address:                Option[Address]           = None
)

object BankAccount {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[BankAccount]
}
