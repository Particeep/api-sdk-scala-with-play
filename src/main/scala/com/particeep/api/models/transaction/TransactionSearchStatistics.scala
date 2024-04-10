package com.particeep.api.models.transaction

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx

case class TransactionSearchStatistics(
  amount:              Long,
  bare_owner_amount:   Long,
  fees:                Long,
  usufructuary_amount: Long,
  partner_fees:        Long,
  num_of_shares:       Long
)

object TransactionSearchStatistics {
  val format = Jsonx.formatCaseClass[TransactionSearchStatistics]
}
