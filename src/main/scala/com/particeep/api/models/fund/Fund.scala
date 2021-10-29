package com.particeep.api.models.fund

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.Currency.{ Currency, EUR }
import com.particeep.api.models.enums.FundStatus.{ FundStatus, INIT }
import play.api.libs.json.JsObject

import java.time.ZonedDateTime

case class Fund(
    id:                           String                = "",
    created_at:                   Option[ZonedDateTime] = None,
    recipient_id:                 Option[String]        = None,
    recipient_type:               Option[String]        = None,
    name:                         String                = "",
    description_short:            Option[String]        = None,
    description_long:             Option[String]        = None,
    description_offline:          Option[String]        = None,
    description_financial:        Option[String]        = None,
    disclaimer_risk:              Option[String]        = None,
    disclaimer_fees:              Option[String]        = None,
    disclaimer_payment:           Option[String]        = None,
    currency:                     Currency              = EUR,
    status:                       FundStatus            = INIT,
    isin_code:                    Option[String]        = None,
    tag:                          Option[String]        = None,
    required_pro:                 Option[Boolean]       = None,
    tax_system:                   Option[String]        = None,
    offer:                        FundOffer             = FundOffer(),
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

object Fund {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  implicit lazy val fund_offer_format = FundOffer.format
  implicit val format = Jsonx.formatCaseClass[Fund]
}
