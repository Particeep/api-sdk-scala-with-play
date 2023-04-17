package com.particeep.api.models.fundraise.equity

import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.Currency
import play.api.libs.json.JsObject

import java.time.OffsetDateTime

case class FundraiseEquityCreation(
    enterprise_id:         Option[String]              = None,
    recipient_id:          Option[String]              = None,
    recipient_type:        Option[String]              = None,
    name:                  String,
    description_short:     Option[String]              = None,
    description_long:      Option[String]              = None,
    description_offline:   Option[String]              = None,
    description_financial: Option[String]              = None,
    disclaimer_risk:       Option[String]              = None,
    disclaimer_fees:       Option[String]              = None,
    disclaimer_payment:    Option[String]              = None,
    start_at:              Option[OffsetDateTime]      = None,
    end_at:                Option[OffsetDateTime]      = None,
    amount_target:         Long,
    amount_target_max:     Option[Long]                = None,
    currency:              Currency,
    score:                 Option[String]              = None,
    tag:                   Option[String]              = None,
    custom:                Option[JsObject]            = None,
    required_pro:          Option[Boolean]             = None,
    unicia_id:             Option[String]              = None,
    dismemberment_table:   Option[Map[String, String]] = None,
    offer:                 Option[EquityOffer]         = None
)

object FundraiseEquityCreation {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit lazy val equity_offer_format = EquityOffer.format
  val format = Jsonx.formatCaseClass[FundraiseEquityCreation]
}
