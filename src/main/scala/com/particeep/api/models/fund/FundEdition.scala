package com.particeep.api.models.fund

import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.Currency.Currency
import org.cvogt.play.json.Jsonx
import play.api.libs.json.JsObject

import java.time.ZonedDateTime

case class FundEdition(
  recipient_id:                 Option[String]        = None,
  recipient_type:               Option[String]        = None,
  name:                         Option[String]        = None,
  description_short:            Option[String]        = None,
  description_long:             Option[String]        = None,
  description_offline:          Option[String]        = None,
  description_financial:        Option[String]        = None,
  disclaimer_risk:              Option[String]        = None,
  disclaimer_fees:              Option[String]        = None,
  disclaimer_payment:           Option[String]        = None,
  currency:                     Option[Currency]      = None,
  tag:                          Option[String]        = None,
  isin_code:                    Option[String]        = None,
  required_pro:                 Option[Boolean]       = None,
  offer:                        Option[FundOffer]     = None,
  tax_system:                   Option[String]        = None,
  logo_url:                     Option[String]        = None,
  image_cover_url:              Option[String]        = None,
  category:                     Option[String]        = None,
  website_url:                  Option[String]        = None,
  dissolve_at:                  Option[ZonedDateTime] = None,
  address:                      Option[Address]       = None,
  form_id:                      Option[String]        = None,
  is_auto_assigned_to_partners: Option[Boolean]       = None,
  custom:                       Option[JsObject]      = None
)

object FundEdition {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  implicit lazy val offer_loan_creation_format = FundOffer.format
  val format = Jsonx.formatCaseClass[FundEdition]
}
