package com.particeep.api.models.fund

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ Format, JsObject, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.Currency.EUR
import com.particeep.api.models.enums.FundStatus.INIT
import com.particeep.api.models.enums.{ Currency, FundStatus, FundraiseFormTag }

case class Fund(
  id:                           String                                = "",
  created_at:                   Option[OffsetDateTime]                = None,
  recipient_id:                 Option[String]                        = None,
  recipient_type:               Option[String]                        = None,
  name:                         String                                = "",
  description_short:            Option[String]                        = None,
  description_long:             Option[String]                        = None,
  description_offline:          Option[String]                        = None,
  description_financial:        Option[String]                        = None,
  disclaimer_risk:              Option[String]                        = None,
  disclaimer_fees:              Option[String]                        = None,
  disclaimer_payment:           Option[String]                        = None,
  currency:                     Currency                              = EUR,
  status:                       FundStatus                            = INIT,
  isin_code:                    Option[String]                        = None,
  tag:                          Option[String]                        = None,
  required_pro:                 Option[Boolean]                       = None,
  tax_system:                   Option[String]                        = None,
  offer:                        FundOffer                             = FundOffer(),
  logo_url:                     Option[String]                        = None,
  image_cover_url:              Option[String]                        = None,
  category:                     Option[String]                        = None,
  website_url:                  Option[String]                        = None,
  dissolve_at:                  Option[OffsetDateTime]                = None,
  address:                      Option[Address]                       = None,
  forms:                        Option[Map[FundraiseFormTag, String]] = None,
  is_auto_assigned_to_partners: Option[Boolean]                       = None,
  custom:                       Option[JsObject]                      = None
)

object Fund {
  implicit val date_format: Writes[OffsetDateTime]        = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address]            = Address.format
  implicit lazy val fund_offer_format: OFormat[FundOffer] = FundOffer.format
  val format: OFormat[Fund]                               = Jsonx.formatCaseClass[Fund]
}
