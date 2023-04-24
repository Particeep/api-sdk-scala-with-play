package com.particeep.api.models.history_price

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class HistoryPrice(
    id:          String                 = "",
    created_at:  Option[OffsetDateTime] = None,
    target_id:   String,
    target_type: String,
    price:       Int
)

object HistoryPrice {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[HistoryPrice]
}
