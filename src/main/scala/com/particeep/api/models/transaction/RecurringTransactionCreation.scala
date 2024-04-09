package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class RecurringTransactionCreation(
  issuer_id: String,
  amount:    Int,
  frequency: Int,
  start_at:  OffsetDateTime,
  duration:  Option[Int] = None
)

object RecurringTransactionCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[RecurringTransactionCreation]
}
