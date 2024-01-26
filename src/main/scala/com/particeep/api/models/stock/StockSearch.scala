package com.particeep.api.models.stock

import java.time.OffsetDateTime

case class StockSearch(
    created_after:    Option[OffsetDateTime] = None,
    created_before:   Option[OffsetDateTime] = None,
    name:             Option[String]         = None,
    description:      Option[String]         = None,
    code_isin:        Option[String]         = None,
    risk_level:       Option[Int]            = None,
    enable_for_sales: Option[Boolean]        = None,
    recipient_id:     Option[String]         = None,
    recipient_type:   Option[String]         = None,
    currency:         Option[String]         = None
)
