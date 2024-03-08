package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class ScheduledPaymentSearch(
  start_payment_date: Option[OffsetDateTime] = None,
  end_payment_date:   Option[OffsetDateTime] = None,
  status:             Option[String]         = None,
  issuer_id:          Option[String]         = None,
  issuer_type:        Option[String]         = None,
  recipient_id:       Option[String]         = None,
  recipient_type:     Option[String]         = None,
  parent_id:          Option[String]         = None,
  parent_type:        Option[String]         = None,
  dependency_id:      Option[String]         = None,
  tag:                Option[String]         = None
)

object ScheduledPaymentSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[ScheduledPaymentSearch]
}
