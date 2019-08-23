package com.particeep.api.models.fund

import java.time.ZonedDateTime

import play.api.libs.json.Json

case class FundOffer(
  fees_in:             Option[Double]        = None,
  fees_in_flat:        Option[Int]           = None,
  fees_out:            Option[Double]        = None,
  fees_out_flat:       Option[Int]           = None,
  min_commitment:      Option[Int]           = None,
  max_commitment:      Option[Long]          = None,
  fund_yield:          Option[Double]        = None,
  current_share_price: Option[Int]           = None,
  share_priced_at:     Option[ZonedDateTime] = None,
  num_of_shares:       Option[Int]           = None,
  net_assets:          Option[Long]          = None
)

object FundOffer {
  val format = Json.format[FundOffer]
}
