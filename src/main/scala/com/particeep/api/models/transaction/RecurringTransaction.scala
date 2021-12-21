package com.particeep.api.models.transaction

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._

case class RecurringTransaction(
    id:             String                = "",
    created_at:     Option[ZonedDateTime] = None,
    issuer_id:      String                = "",
    issuer_name:    String                = "",
    amount:         Int                   = 0,
    recipient_id:   String                = "",
    recipient_name: String                = "",
    recipient_type: String                = "",
    frequency:      Int                   = 0,
    start_at:       Option[ZonedDateTime] = None,
    duration:       Option[Int]           = None
)

object RecurringTransaction {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Jsonx.formatCaseClass[RecurringTransaction]
}
