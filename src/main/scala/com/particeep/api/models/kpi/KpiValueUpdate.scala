package com.particeep.api.models.kpi

import java.time.OffsetDateTime
import play.api.libs.json.Json
import com.particeep.api.core.Formatter

case class KpiValueUpdate(
    id:    String,
    date:  OffsetDateTime,
    value: Double
)

object KpiValueUpdate {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit val format = Json.format[KpiValueUpdate]
}
