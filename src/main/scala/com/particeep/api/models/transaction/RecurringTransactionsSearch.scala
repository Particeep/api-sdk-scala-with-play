package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class RecurringTransactionsSearch(
  created_start_date: Option[OffsetDateTime] = None,
  created_end_date:   Option[OffsetDateTime] = None,
  start_date:         Option[OffsetDateTime] = None,
  end_date:           Option[OffsetDateTime] = None,
  issuer_id:          Option[String]         = None,
  issuer_name:        Option[String]         = None,
  recipient_id:       Option[String]         = None,
  recipient_name:     Option[String]         = None,
  recipient_type:     Option[String]         = None,
  amount:             Option[Int]            = None,
  frequency:          Option[Int]            = None,
  duration:           Option[Int]            = None
)

object RecurringTransactionsSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[RecurringTransactionsSearch]
}
