package com.particeep.api.models.history_price

case class HistoryPriceSearch(
    target_id:   Option[String] = None,
    target_type: Option[String] = None
)
