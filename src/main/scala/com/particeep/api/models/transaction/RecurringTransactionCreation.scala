package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class RecurringTransactionCreation(
  transaction_id: String,
  amount:         Int,
  start_at:       OffsetDateTime,
  frequency:      Int         = 0,
  duration:       Option[Int] = None
)

object RecurringTransactionCreation {
  implicit val date_format: Writes[OffsetDateTime]  = Formatter.OffsetDateTimeWrites
  val format: OFormat[RecurringTransactionCreation] = Json.format[RecurringTransactionCreation]
}
