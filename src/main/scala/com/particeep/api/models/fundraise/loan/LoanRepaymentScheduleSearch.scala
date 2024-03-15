package com.particeep.api.models.fundraise.loan

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

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
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[LoanRepaymentScheduleSearch]
}
