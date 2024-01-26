package com.particeep.api.models.stock

import play.api.libs.json.Json

case class StockCompositionCreation(
    inner_product_id: String,
    percentage:       Int
)

object StockCompositionCreation {
  val format = Json.format[StockCompositionCreation]
}
