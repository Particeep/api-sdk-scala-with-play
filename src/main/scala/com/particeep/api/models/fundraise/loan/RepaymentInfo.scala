package com.particeep.api.models.fundraise.loan

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.CalculatorType.CalculatorType

case class RepaymentInfo(
  val method:               CalculatorType,
  val repayment_frequency:  Option[Int],
  val repayment_start_date: OffsetDateTime,
  val deferred_period:      Option[Int]     = None,
  val does_pay_taxes:       Option[Boolean] = None,
  val amount:               Option[Int]     = None
)

object RepaymentInfo {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[RepaymentInfo]
}
