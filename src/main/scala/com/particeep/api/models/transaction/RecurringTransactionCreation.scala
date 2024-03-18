package com.particeep.api.models.transaction

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, Writes }

case class RecurringTransactionCreation(
    issuer_id: String,
    amount:    Int,
    frequency: Int,
    start_at:  OffsetDateTime,
    duration:  Option[Int]    = None
)

object RecurringTransactionCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[RecurringTransactionCreation]
}
