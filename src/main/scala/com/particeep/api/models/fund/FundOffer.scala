package com.particeep.api.models.fund

import play.api.libs.json.Json

case class FundOffer(
  fees_in:        Option[Double] = None,
  fees_in_flat:   Option[Int]    = None,
  fees_out:       Option[Double] = None,
  min_commitment: Option[Int]    = None,
  max_commitment: Option[Long]   = None
)

object FundOffer {
  val format = Json.format[FundOffer]
}
