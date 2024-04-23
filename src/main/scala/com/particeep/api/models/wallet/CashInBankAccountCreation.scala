package com.particeep.api.models.wallet

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.Country.Country

case class CashInBankAccountCreation(
  amount:   Int,
  fees:     Option[Int]     = None,
  owner_ip: Option[String]  = None,
  country:  Option[Country] = None
)

object CashInBankAccountCreation {
  val format: OFormat[CashInBankAccountCreation] = Json.format[CashInBankAccountCreation]
}
