package com.particeep.api.models.wallet

import play.api.data.Form
import play.api.data.Forms.{ mapping, optional, text }

case class BankAccountSearch(
  id:          Option[String] = None,
  target_id:   Option[String] = None,
  target_type: Option[String] = None,
  wallet_id:   Option[String] = None,
  status:      Option[String] = None,
  bank_name:   Option[String] = None,
  iban:        Option[String] = None,
  bic:         Option[String] = None,
  acct_num:    Option[String] = None,
  aba_num:     Option[String] = None,
  transit_num: Option[String] = None
)

object BankAccountSearch {
  val bank_account_search_form: Form[BankAccountSearch] = Form(
    mapping(
      "id"          -> optional(text),
      "target_id"   -> optional(text),
      "target_type" -> optional(text),
      "wallet_id"   -> optional(text),
      "status"      -> optional(text),
      "bank_name"   -> optional(text),
      "iban"        -> optional(text),
      "bic"         -> optional(text),
      "acct_num"    -> optional(text),
      "aba_num"     -> optional(text),
      "transit_num" -> optional(text)
    )(BankAccountSearch.apply)(BankAccountSearch.unapply)
  )
}
