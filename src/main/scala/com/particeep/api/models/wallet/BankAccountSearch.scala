package com.particeep.api.models.wallet

import play.api.libs.json.Json

case class BankAccountSearch (
  id:          Option[String]   = None,
  target_id:   Option[String]   = None,
  target_type: Option[String]   = None,
  wallet_id:   Option[String]   = None,
  status:      Option[String]   = None,
  bank_name:   Option[String]   = None,
  iban:        Option[String]   = None,
  bic:         Option[String]   = None,
  acct_num:    Option[String]   = None,
  aba_num:     Option[String]   = None,
  transit_num: Option[String]   = None
)

object BankAccountSearch {
  val format = Json.format[BankAccountSearch]
}