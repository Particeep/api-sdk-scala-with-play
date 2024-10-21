package com.particeep.api.models.unicia

import play.api.libs.json.{Json, OFormat}

import java.time.OffsetDateTime

case class ClientIncomeUnicia(
  product_name:             String,
  taxable:                  String,
  fees:                     Double,
  payment_since_year_start: Int,
  update_at:                Option[OffsetDateTime] = None
)

object ClientIncomeUnicia {
  implicit val format: OFormat[ClientIncomeUnicia] = Json.format[ClientIncomeUnicia]
}
