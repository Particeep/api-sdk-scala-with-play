package com.particeep.api.models.fundraise.equity

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ JsObject, JsString, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.{ Currency, FundraiseFormTag }

case class FundraiseEquityEdition(
  recipient_id:                    Option[String]                        = None,
  recipient_type:                  Option[String]                        = None,
  name:                            Option[String]                        = None,
  description_short:               Option[String]                        = None,
  description_long:                Option[String]                        = None,
  description_offline:             Option[String]                        = None,
  description_financial:           Option[String]                        = None,
  disclaimer_risk:                 Option[String]                        = None,
  disclaimer_fees:                 Option[String]                        = None,
  disclaimer_payment:              Option[String]                        = None,
  url:                             Option[String]                        = None,
  start_at:                        Option[OffsetDateTime]                = None,
  end_at:                          Option[OffsetDateTime]                = None,
  amount_target:                   Option[Long]                          = None,
  amount_target_max:               Option[Long]                          = None,
  currency:                        Option[Currency]                      = None,
  score:                           Option[String]                        = None,
  tag:                             Option[String]                        = None,
  custom:                          Option[JsObject]                      = None,
  required_pro:                    Option[Boolean]                       = None,
  is_featured:                     Option[Boolean]                       = None,
  recurring_investment_activated:  Option[Boolean]                       = None,
  recurring_investment_frequency:  Option[Int]                           = None,
  recurring_investment_min_amount: Option[Int]                           = None,
  forms:                           Option[Map[FundraiseFormTag, String]] = None,
  unicia_id:                       Option[String]                        = None,
  dismemberment_table:             Option[Map[String, String]]           = None,
  offer:                           Option[EquityOffer]                   = None
)

object FundraiseEquityEdition {
  implicit val date_format: Writes[OffsetDateTime]                 = Formatter.OffsetDateTimeWrites
  implicit lazy val equity_offer_format: OFormat[EquityOffer]      = EquityOffer.format
  implicit val forms_writes: Writes[Map[FundraiseFormTag, String]] = Writes { value =>
    JsObject(value.map { case (key, value) => key.toString -> JsString(value) })
  }
  val format: OFormat[FundraiseEquityEdition]                      = Jsonx.formatCaseClassUseDefaults[FundraiseEquityEdition]
}
