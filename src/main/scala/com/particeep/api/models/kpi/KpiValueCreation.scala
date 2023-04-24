package com.particeep.api.models.kpi

import java.time.OffsetDateTime
import play.api.libs.json.Json
import com.particeep.api.core.Formatter

case class KpiValueCreation(
    date:    OffsetDateTime,
    value:   Double,
    comment: Option[String] = None,
    tag:     Option[String] = None
)

object KpiValueCreation {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit val format = Json.format[KpiValueCreation]
}
