package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.CalculatorType

case class LoanOfferEdition(
  term:                 Option[Int]            = None,
  rate:                 Option[Double]         = None,
  tax_rate:             Option[Double]         = None,
  fees_in:              Option[Double]         = None,
  fees_in_flat:         Option[Int]            = None,
  step:                 Option[Int]            = None,
  amount_min:           Option[Int]            = None,
  amount_max:           Option[Int]            = None,
  bond_price:           Option[Int]            = None,
  method:               Option[CalculatorType] = None,
  repayment_frequency:  Option[Int]            = None,
  repayment_start_date: Option[OffsetDateTime] = None,
  deferred_period:      Option[Int]            = None,
  custom_schedule:      Option[JsObject]       = None
)

object LoanOfferEdition {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[LoanOfferEdition]            = Json.format[LoanOfferEdition]
}
