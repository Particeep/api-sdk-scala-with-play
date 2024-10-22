package com.particeep.api.models.unicia

import play.api.libs.json.{ Json, OFormat }

import java.time.OffsetDateTime

case class ClientWalletUnicia(
  product_name:      String,
  property_nature:   Option[String]         = None,
  part_start:        Int,
  part_end:          Int,
  purchase_at:       OffsetDateTime,
  enjoyment_year:    Int,
  part_nb:           Int,
  total_investment:  Double,
  current_valuation: Double,
  performance:       Double,
  update_at:         Option[OffsetDateTime] = None
)

object ClientWalletUnicia {
  implicit val format: OFormat[ClientWalletUnicia] = Json.format[ClientWalletUnicia]
}
