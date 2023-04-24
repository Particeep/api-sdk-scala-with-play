package com.particeep.api.models.fundraise.loan

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.CalculatorType.CalculatorType
import play.api.libs.json.{ JsObject, Json }

case class LoanOffer(
    term:                 Int                    = 0,
    rate:                 Double                 = 0,
    tax_rate:             Double                 = 0,
    fees_in:              Option[Double]         = None,
    fees_in_flat:         Option[Int]            = None,
    step:                 Int                    = 1,
    amount_min:           Option[Int]            = None,
    amount_max:           Option[Int]            = None,
    bond_price:           Option[Int]            = None,
    method:               Option[CalculatorType] = None,
    repayment_frequency:  Option[Int]            = None,
    repayment_start_date: Option[OffsetDateTime] = None,
    deferred_period:      Option[Int]            = None,
    custom_schedule:      Option[JsObject]       = None
)

object LoanOffer {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[LoanOffer]
}
