package com.particeep.api.models.history_price

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class HistoryPrice(
    id:          String                = "",
    created_at:  Option[ZonedDateTime] = None,
    target_id:   String,
    target_type: String,
    price:       Int
)

object HistoryPrice {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[HistoryPrice]
}
