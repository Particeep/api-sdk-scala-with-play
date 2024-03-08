package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.Currency

case class FundraiseLoanEdition(
  name:                  Option[String]           = None,
  description_short:     Option[String]           = None,
  description_long:      Option[String]           = None,
  description_offline:   Option[String]           = None,
  description_financial: Option[String]           = None,
  disclaimer_risk:       Option[String]           = None,
  disclaimer_fees:       Option[String]           = None,
  disclaimer_payment:    Option[String]           = None,
  recipient_id:          Option[String]           = None,
  recipient_type:        Option[String]           = None,
  start_at:              Option[OffsetDateTime]   = None,
  end_at:                Option[OffsetDateTime]   = None,
  amount_target:         Option[Long]             = None,
  amount_target_max:     Option[Long]             = None,
  currency:              Option[Currency]         = None,
  score:                 Option[String]           = None,
  is_featured:           Option[Boolean]          = None,
  form_id:               Option[String]           = None,
  tag:                   Option[String]           = None,
  offer:                 Option[LoanOfferEdition] = None,
  custom:                Option[JsObject]         = None
)

object FundraiseLoanEdition {
  implicit val date_format: Writes[OffsetDateTime]                       = Formatter.OffsetDateTimeWrites
  implicit lazy val loan_offer_edition_format: OFormat[LoanOfferEdition] = LoanOfferEdition.format
  val format                                                             = Json.format[FundraiseLoanEdition]
}
