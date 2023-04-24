package com.particeep.api.models.transaction

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._

case class RecurringTransaction(
    id:             String                 = "",
    created_at:     Option[OffsetDateTime] = None,
    issuer_id:      String                 = "",
    issuer_name:    String                 = "",
    amount:         Int                    = 0,
    recipient_id:   String                 = "",
    recipient_name: String                 = "",
    recipient_type: String                 = "",
    frequency:      Int                    = 0,
    start_at:       Option[OffsetDateTime] = None,
    duration:       Option[Int]            = None
)

object RecurringTransaction {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Jsonx.formatCaseClass[RecurringTransaction]
}
