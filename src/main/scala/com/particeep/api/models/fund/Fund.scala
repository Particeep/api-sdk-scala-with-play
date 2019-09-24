package com.particeep.api.models.fund

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.{ Currency, EUR }
import com.particeep.api.models.enums.FundStatus.{ FundStatus, INIT }
import play.api.libs.json.{ JsObject, Json }

case class Fund(
  id:                  String                = "",
  created_at:          Option[ZonedDateTime] = None,
  created_by:          Option[String]        = None,
  enterprise_id:       Option[String]        = None,
  recipient_id:        Option[String]        = None,
  recipient_type:      Option[String]        = None,
  name:                String                = "",
  description_short:   Option[String]        = None,
  description_long:    Option[String]        = None,
  description_offline: Option[String]        = None,
  disclaimer_risk:     Option[String]        = None,
  disclaimer_fees:     Option[String]        = None,
  disclaimer_payment:  Option[String]        = None,
  currency:            Currency              = EUR,
  status:              FundStatus            = INIT,
  isin_code:           Option[String]        = None,
  tag:                 Option[String]        = None,
  required_pro:        Option[Boolean]       = None,
  tax_system:          Option[String]        = None,
  offer:               FundOffer             = FundOffer(),
  custom:              Option[JsObject]      = None
)

object Fund {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  implicit lazy val fund_offer_format = FundOffer.format
  val format = Json.format[Fund]
}
