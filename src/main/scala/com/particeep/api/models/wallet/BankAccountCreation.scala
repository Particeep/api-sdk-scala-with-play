package com.particeep.api.models.wallet

import play.api.libs.json.Json

case class BankAccountCreation(
  bank_name:    String,
  iban:         String,
  bic:          Option[String] = None,
  number:       Option[String] = None,
  street:       String,
  zip:          String,
  city:         String,
  country:      String,
  holder_name:  Option[String] = None,
  acct_num:     Option[String] = None,
  aba_num:      Option[String] = None,
  transit_num:  Option[String] = None,
  owner_ip:     Option[String] = None,
  account_type: Option[String] = None,
  target_id:    Option[String] = None
)

object BankAccountCreation {
  val format = Json.format[BankAccountCreation]
}
