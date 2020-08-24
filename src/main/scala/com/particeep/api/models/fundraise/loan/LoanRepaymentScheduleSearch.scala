package com.particeep.api.models.fundraise.loan

import java.time.ZonedDateTime

case class LoanRepaymentScheduleSearch(
  payment_date_after:  Option[ZonedDateTime] = None,
  payment_date_before: Option[ZonedDateTime] = None,
  user_id:             Option[String]        = None,
  enterprise_id:       Option[String]        = None,
  fundraise_id:        Option[String]        = None,
  transaction_id:      Option[String]        = None,
  is_amount_paid:      Option[Boolean]       = None,
  is_tax_paid:         Option[Boolean]       = None,
  is_offline:          Option[Boolean]       = None,
  tag:                 Option[String]        = None
)
