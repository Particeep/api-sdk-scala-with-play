package com.particeep.api.models.fund

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ JsObject, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class FundData(
  id:                           String                 = "",
  created_at:                   Option[OffsetDateTime] = None,
  recipient_id:                 Option[String]         = None,
  recipient_type:               Option[String]         = None,
  recipient_email:              Option[String]         = None,
  name:                         Option[String]         = None,
  description_short:            Option[String]         = None,
  description_long:             Option[String]         = None,
  description_offline:          Option[String]         = None,
  description_financial:        Option[String]         = None,
  disclaimer_risk:              Option[String]         = None,
  disclaimer_fees:              Option[String]         = None,
  disclaimer_payment:           Option[String]         = None,
  currency:                     Option[String]         = None,
  status:                       Option[String]         = None,
  amount_engaged:               Option[Long]           = None,
  transaction_count:            Option[Int]            = None,
  tag:                          Option[String]         = None,
  isin_code:                    Option[String]         = None,
  required_pro:                 Option[Boolean]        = None,
  tax_system:                   Option[String]         = None,
  offer:                        FundOffer              = FundOffer(),
  logo_url:                     Option[String]         = None,
  image_cover_url:              Option[String]         = None,
  category:                     Option[String]         = None,
  website_url:                  Option[String]         = None,
  dissolve_at:                  Option[OffsetDateTime] = None,
  targeting_roles:              Option[String]         = None,
  is_auto_assigned_to_partners: Option[Boolean]        = None,
  custom:                       Option[JsObject]       = None
)

object FundData {
  implicit val date_format: Writes[OffsetDateTime]        = Formatter.OffsetDateTimeWrites
  implicit lazy val fund_offer_format: OFormat[FundOffer] = FundOffer.format
  val format: OFormat[FundData]                           = Jsonx.formatCaseClass[FundData]
}
