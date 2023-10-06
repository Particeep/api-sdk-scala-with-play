package com.particeep.api.models.transaction

import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import com.particeep.api.core.Formatter

case class TransactionSearchStatistics(
    amount:              Double,
    bare_owner_amount:   Double,
    fees:                Double,
    usufructuary_amount: Double,
    partner_fees:        Double,
    num_of_shares:       Long
)

object TransactionSearchStatistics {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit val format = Jsonx.formatCaseClass[TransactionSearchStatistics]
}
