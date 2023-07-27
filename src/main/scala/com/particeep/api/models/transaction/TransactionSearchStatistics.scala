package com.particeep.api.models.transaction

import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import com.particeep.api.core.Formatter

case class TransactionSearchStatistics(
    amount:              Long,
    bare_owner_amount:   Long,
    fees:                Long,
    usufructuary_amount: Long,
    partner_fees:        Long,
    num_of_shares:       Long
)

object TransactionSearchStatistics {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Jsonx.formatCaseClass[TransactionSearchStatistics]
}
