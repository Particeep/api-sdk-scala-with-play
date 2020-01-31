package com.particeep.api.models.transaction

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import org.cvogt.play.json.Jsonx

case class RecurringTransaction(
  id:             String                = "",
  created_at:     Option[ZonedDateTime] = None,
  user_id:        String                = "",
  amount:         Int                   = 0,
  fundraise_id:   String                = "",
  fundraise_type: String                = "",
  frequency:      Int                   = 0,
  start_at:       Option[ZonedDateTime] = None,
  duration:       Option[Int]           = None
)

object RecurringTransaction {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Jsonx.formatCaseClass[RecurringTransaction]
}
