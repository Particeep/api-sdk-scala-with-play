package com.particeep.api.models.transaction

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class RecurringTransactionCreation(
    issuer_id: String,
    amount:    Int,
    frequency: Int,
    start_at:  ZonedDateTime,
    duration:  Option[Int]   = None
)

object RecurringTransactionCreation {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  implicit val format = Json.format[RecurringTransactionCreation]
}
