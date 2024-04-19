package com.particeep.api.models.fund

import play.api.libs.json.{Json, OFormat, Writes}

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter

case class FundOffer(
  fees_in:                  Option[Double]         = None,
  fees_in_flat:             Option[Int]            = None,
  fees_out:                 Option[Double]         = None,
  fees_out_flat:            Option[Int]            = None,
  min_commitment:           Option[Int]            = None,
  max_commitment:           Option[Long]           = None,
  fund_yield:               Option[Double]         = None,
  current_share_price:      Option[Int]            = None,
  share_priced_at:          Option[OffsetDateTime] = None,
  num_of_shares:            Option[Int]            = None,
  step:                     Option[Int]            = None,
  net_assets:               Option[Long]           = None,
  partner_fees_lower_range: Option[Double]         = None,
  partner_fees_upper_range: Option[Double]         = None
)

object FundOffer {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[FundOffer] = Json.format[FundOffer]
}
