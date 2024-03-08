package com.particeep.api.models.history_price

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class HistoryPrice(
  id:          String                 = "",
  created_at:  Option[OffsetDateTime] = None,
  target_id:   String,
  target_type: String,
  price:       Int
)

object HistoryPrice {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[HistoryPrice]
}
