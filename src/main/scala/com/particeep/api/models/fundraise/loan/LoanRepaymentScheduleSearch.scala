package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class LoanRepaymentScheduleSearch(
  payment_date_after:  Option[OffsetDateTime] = None,
  payment_date_before: Option[OffsetDateTime] = None,
  user_id:             Option[String]         = None,
  enterprise_id:       Option[String]         = None,
  fundraise_id:        Option[String]         = None,
  transaction_id:      Option[String]         = None,
  is_amount_paid:      Option[Boolean]        = None,
  is_tax_paid:         Option[Boolean]        = None,
  is_offline:          Option[Boolean]        = None,
  tag:                 Option[String]         = None
)

object LoanRepaymentScheduleSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[LoanRepaymentScheduleSearch] = Json.format[LoanRepaymentScheduleSearch]
}
