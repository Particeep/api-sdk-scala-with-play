package com.particeep.api.models.unicia

import play.api.libs.json.{ Json, OFormat }

import java.time.OffsetDateTime

case class ClientTaxUnicia(
  product_name:    String,
  year:            Int,
  gain_or_loss:    Double,
  net_land_income: Double,
  update_at:       Option[OffsetDateTime]
)

object ClientTaxUnicia {
  implicit val format: OFormat[ClientTaxUnicia] = Json.format[ClientTaxUnicia]
}
