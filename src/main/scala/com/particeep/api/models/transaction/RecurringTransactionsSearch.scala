package com.particeep.api.models.transaction

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

import java.time.ZonedDateTime

case class RecurringTransactionsSearch(
    created_start_date: Option[ZonedDateTime] = None,
    created_end_date:   Option[ZonedDateTime] = None,
    start_date:         Option[ZonedDateTime] = None,
    end_date:           Option[ZonedDateTime] = None,
    issuer_id:          Option[String]        = None,
    issuer_name:        Option[String]        = None,
    recipient_id:       Option[String]        = None,
    recipient_name:     Option[String]        = None,
    recipient_type:     Option[String]        = None,
    amount:             Option[Int]           = None,
    frequency:          Option[Int]           = None,
    duration:           Option[Int]           = None
)

object RecurringTransactionsSearch {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[RecurringTransactionsSearch]
}
