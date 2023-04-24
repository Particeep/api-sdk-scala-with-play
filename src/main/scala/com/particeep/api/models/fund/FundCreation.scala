package com.particeep.api.models.fund

import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.{ Currency, EUR }
import play.api.libs.json.JsObject
import com.particeep.api.models.Address

case class FundCreation(
    recipient_id:                 Option[String]         = None,
    recipient_type:               Option[String]         = None,
    name:                         String,
    description_short:            Option[String]         = None,
    description_long:             Option[String]         = None,
    description_offline:          Option[String]         = None,
    description_financial:        Option[String]         = None,
    disclaimer_risk:              Option[String]         = None,
    disclaimer_fees:              Option[String]         = None,
    disclaimer_payment:           Option[String]         = None,
    isin_code:                    Option[String]         = None,
    currency:                     Currency               = EUR,
    tag:                          Option[String]         = None,
    required_pro:                 Option[Boolean]        = None,
    tax_system:                   Option[String]         = None,
    offer:                        Option[FundOffer]      = None,
    logo_url:                     Option[String]         = None,
    image_cover_url:              Option[String]         = None,
    category:                     Option[String]         = None,
    website_url:                  Option[String]         = None,
    dissolve_at:                  Option[OffsetDateTime] = None,
    address:                      Option[Address]        = None,
    is_auto_assigned_to_partners: Option[Boolean]        = None,
    custom:                       Option[JsObject]       = None
)

object FundCreation {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit lazy val offer_loan_creation_format = FundOffer.format
  val format = Jsonx.formatCaseClass[FundCreation]
}
