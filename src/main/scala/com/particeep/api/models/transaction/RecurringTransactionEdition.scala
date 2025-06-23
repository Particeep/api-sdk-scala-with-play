package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class RecurringTransactionEdition(
  amount:    Option[Int]            = None,
  frequency: Option[Int]            = None,
  start_at:  Option[OffsetDateTime] = None,
  duration:  Option[Int]            = None
)

object RecurringTransactionEdition {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[RecurringTransactionEdition] = Json.format[RecurringTransactionEdition]
}
