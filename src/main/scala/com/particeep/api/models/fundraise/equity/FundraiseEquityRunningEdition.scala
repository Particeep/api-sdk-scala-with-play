package com.particeep.api.models.fundraise.equity

import play.api.libs.json.{ JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class FundraiseEquityRunningEdition(
  name:                  Option[String]             = None,
  description_short:     Option[String]             = None,
  description_long:      Option[String]             = None,
  description_offline:   Option[String]             = None,
  description_financial: Option[String]             = None,
  disclaimer_risk:       Option[String]             = None,
  disclaimer_fees:       Option[String]             = None,
  disclaimer_payment:    Option[String]             = None,
  url:                   Option[String]             = None,
  end_at:                Option[OffsetDateTime]     = None,
  amount_target:         Option[Long]               = None,
  amount_target_max:     Option[Long]               = None,
  score:                 Option[String]             = None,
  form_id:               Option[String]             = None,
  modification_form_id:  Option[String]             = None,
  cancellation_form_id:  Option[String]             = None,
  unicia_id:             Option[String]             = None,
  tag:                   Option[String]             = None,
  is_featured:           Option[Boolean]            = None,
  required_pro:          Option[Boolean]            = None,
  custom:                Option[JsObject]           = None,
  offer:                 Option[RunningEquityOffer] = None
)

object FundraiseEquityRunningEdition {
  implicit val date_format: Writes[OffsetDateTime]                      = Formatter.OffsetDateTimeWrites
  implicit val running_equity_offer_format: OFormat[RunningEquityOffer] = RunningEquityOffer.format
  val format: OFormat[FundraiseEquityRunningEdition]                    = Json.format[FundraiseEquityRunningEdition]
}
