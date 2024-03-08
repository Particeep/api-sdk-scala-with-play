package com.particeep.api.models.fundraise.equity

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.{ Currency, EUR }
import com.particeep.api.models.enums.FundraiseStatus.{ FundraiseStatus, INIT }

case class FundraiseEquity(
  id:                    String                      = "",
  created_at:            Option[OffsetDateTime]      = None,
  enterprise_id:         Option[String]              = None,
  recipient_id:          Option[String]              = None,
  recipient_type:        Option[String]              = None,
  name:                  String                      = "",
  description_short:     Option[String]              = None,
  description_long:      Option[String]              = None,
  description_offline:   Option[String]              = None,
  description_financial: Option[String]              = None,
  disclaimer_risk:       Option[String]              = None,
  disclaimer_fees:       Option[String]              = None,
  disclaimer_payment:    Option[String]              = None,
  start_at:              Option[OffsetDateTime]      = None,
  end_at:                Option[OffsetDateTime]      = None,
  amount_target:         Long                        = 0,
  amount_target_max:     Option[Long]                = None,
  currency:              Currency                    = EUR,
  status:                FundraiseStatus             = INIT,
  score:                 Option[String]              = None,
  tag:                   Option[String]              = None,
  custom:                Option[JsObject]            = None,
  private_group_id:      Option[String]              = None,
  required_pro:          Option[Boolean]             = None,
  is_featured:           Option[Boolean]             = None,
  form_id:               Option[String]              = None,
  unicia_id:             Option[String]              = None,
  dismemberment_table:   Option[Map[String, String]] = None,
  offer:                 EquityOffer                 = EquityOffer()
)

object FundraiseEquity {
  implicit val date_format: Writes[OffsetDateTime]            = Formatter.OffsetDateTimeWrites
  implicit lazy val equity_offer_format: OFormat[EquityOffer] = EquityOffer.format
  val format                                                  = Jsonx.formatCaseClass[FundraiseEquity]
}
