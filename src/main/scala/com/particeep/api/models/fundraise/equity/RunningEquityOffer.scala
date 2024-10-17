package com.particeep.api.models.fundraise.equity

import play.api.libs.json.{ Json, OFormat }

case class RunningEquityOffer (
  price_per_share:     Option[Int]    = None,
  total_shares:        Option[Long]   = None,
  min_commitment:      Option[Int]    = Some(1),
  max_commitment:      Option[Long]   = None
                              )
object RunningEquityOffer {
  val format: OFormat[RunningEquityOffer] = Json.format[RunningEquityOffer]
}

