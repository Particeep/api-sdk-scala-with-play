package com.particeep.api.models.transaction

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class RecurringTransaction(
  id:             String                 = "",
  created_at:     Option[OffsetDateTime] = None,
  transaction_id: String                 = "",
  amount:         Int                    = 0,
  frequency:      Int                    = 0,
  start_at:       Option[OffsetDateTime] = None,
  duration:       Option[Int]            = None
)

object RecurringTransaction {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[RecurringTransaction]        = Jsonx.formatCaseClass[RecurringTransaction]
}
