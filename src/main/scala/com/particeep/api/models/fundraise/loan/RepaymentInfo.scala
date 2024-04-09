package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.CalculatorType.CalculatorType

case class RepaymentInfo(
  method:               CalculatorType,
  repayment_frequency:  Option[Int],
  repayment_start_date: OffsetDateTime,
  deferred_period:      Option[Int]     = None,
  does_pay_taxes:       Option[Boolean] = None,
  amount:               Option[Int]     = None
)

object RepaymentInfo {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[RepaymentInfo]
}
