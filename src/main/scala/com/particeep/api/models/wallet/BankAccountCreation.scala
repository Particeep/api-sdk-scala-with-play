package com.particeep.api.models.wallet

import play.api.libs.json.Json

case class BankAccountCreation(
    bank_name:              String,
    iban:                   String,
    bic:                    Option[String] = None,
    number:                 Option[String] = None,
    street:                 Option[String] = None,
    zip:                    Option[String] = None,
    city:                   Option[String] = None,
    country:                Option[String] = None,
    holder_name:            Option[String] = None,
    acct_num:               Option[String] = None,
    aba_num:                Option[String] = None,
    transit_num:            Option[String] = None,
    owner_ip:               Option[String] = None,
    account_type:           Option[String] = None,
    justificatory_document: Option[String] = None,
    target_id:              Option[String] = None
)

object BankAccountCreation {
  val format = Json.format[BankAccountCreation]
}
