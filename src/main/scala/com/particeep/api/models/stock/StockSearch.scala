package com.particeep.api.models.stock

case class StockSearch(
  name:             Option[String],
  description:      Option[String],
  code_isin:        Option[String],
  risk_level:       Option[Int],
  enable_for_sales: Option[Boolean]
)
