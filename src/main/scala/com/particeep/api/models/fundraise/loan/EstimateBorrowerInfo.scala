package com.particeep.api.models.fundraise.loan

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.CalculatorType.CalculatorType

case class EstimateBorrowerInfo(
  amount:               Int,
  term:                 Int,
  rate:                 Double,
  tax_rate:             Double,
  step:                 Int,
  method:               CalculatorType,
  repayment_frequency:  Int,
  repayment_start_date: OffsetDateTime,
  deferred_period:      Option[Int] = None,
  does_pay_taxes:       Boolean     = false
)

object EstimateBorrowerInfo {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[EstimateBorrowerInfo]
}
