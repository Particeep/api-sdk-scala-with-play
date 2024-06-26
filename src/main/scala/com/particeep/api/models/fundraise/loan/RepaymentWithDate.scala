package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class Repayment(
  capital:                        Int,
  capital_offline:                Option[Int] = None,
  interest:                       Int,
  interest_offline:               Option[Int] = None,
  taxes:                          Int,
  amount:                         Int,
  amount_offline:                 Option[Int] = None,
  capital_remains_to_pay:         Int,
  capital_remains_to_pay_offline: Option[Int] = None,
  fees:                           Int,
  fees_offline:                   Option[Int] = None,
  is_paid:                        Boolean,
  is_paid_offline:                Boolean
)

case class RepaymentWithDate(
  date:      OffsetDateTime,
  repayment: Repayment
)

object RepaymentWithDate {
  implicit val date_format: Writes[OffsetDateTime]  = Formatter.OffsetDateTimeWrites
  implicit val repayment_format: OFormat[Repayment] = Json.format[Repayment]
  val format: OFormat[RepaymentWithDate]            = Json.format[RepaymentWithDate]
}
