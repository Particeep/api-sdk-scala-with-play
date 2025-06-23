package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class RecurringTransactionsSearch(
  created_start_date: Option[OffsetDateTime] = None,
  created_end_date:   Option[OffsetDateTime] = None,
  start_date:         Option[OffsetDateTime] = None,
  end_date:           Option[OffsetDateTime] = None,
  transaction_id:     Option[String],
  amount:             Option[Int]            = None,
  frequency:          Option[Int]            = None,
  duration:           Option[Int]            = None
)

object RecurringTransactionsSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[RecurringTransactionsSearch] = Json.format[RecurringTransactionsSearch]
}
