package com.particeep.api.models.fund

import com.particeep.api.models.enums.Currency.Currency
import play.api.libs.json.{ JsObject, Json }

case class FundEdition(
  recipient_id:        Option[String]    = None,
  recipient_type:      Option[String]    = None,
  name:                Option[String]    = None,
  description_short:   Option[String]    = None,
  description_long:    Option[String]    = None,
  description_offline: Option[String]    = None,
  disclaimer_risk:     Option[String]    = None,
  disclaimer_fees:     Option[String]    = None,
  disclaimer_payment:  Option[String]    = None,
  currency:            Option[Currency]  = None,
  tag:                 Option[String]    = None,
  isin_code:           Option[String]    = None,
  required_pro:        Option[Boolean]   = None,
  offer:               Option[FundOffer] = None,
  tax_system:          Option[String]    = None,
  custom:              Option[JsObject]  = None
)

object FundEdition {
  implicit lazy val offer_loan_creation_format = FundOffer.format
  val format = Json.format[FundEdition]
}
