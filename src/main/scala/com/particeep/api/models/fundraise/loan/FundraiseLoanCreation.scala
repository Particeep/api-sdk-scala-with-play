package com.particeep.api.models.fundraise.loan

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.Currency

case class FundraiseLoanCreation(
  name:                  String,
  description_short:     Option[String]         = None,
  description_long:      Option[String]         = None,
  description_offline:   Option[String]         = None,
  description_financial: Option[String]         = None,
  disclaimer_risk:       Option[String]         = None,
  disclaimer_fees:       Option[String]         = None,
  disclaimer_payment:    Option[String]         = None,
  enterprise_id:         Option[String]         = None,
  recipient_id:          Option[String]         = None,
  recipient_type:        Option[String]         = None,
  start_at:              Option[OffsetDateTime] = None,
  end_at:                Option[OffsetDateTime] = None,
  amount_target:         Long,
  amount_target_max:     Option[Long]           = None,
  currency:              Currency,
  score:                 Option[String]         = None,
  tag:                   Option[String]         = None,
  offer:                 LoanOfferCreation,
  custom:                Option[JsObject]       = None
)

object FundraiseLoanCreation {
  implicit val date_format: Writes[OffsetDateTime]                         = Formatter.OffsetDateTimeWrites
  implicit lazy val offer_loan_creation_format: OFormat[LoanOfferCreation] = LoanOfferCreation.format
  val format                                                               = Json.format[FundraiseLoanCreation]
}
