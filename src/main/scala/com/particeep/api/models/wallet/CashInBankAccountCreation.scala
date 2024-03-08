package com.particeep.api.models.wallet

import play.api.libs.json.Json

import com.particeep.api.models.enums.Country.Country

case class CashInBankAccountCreation(
    amount:   Int,
    fees:     Option[Int]     = None,
    owner_ip: Option[String]  = None,
    country:  Option[Country] = None
)

object CashInBankAccountCreation {
  val format = Json.format[CashInBankAccountCreation]
}
