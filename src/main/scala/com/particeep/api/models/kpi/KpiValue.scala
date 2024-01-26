package com.particeep.api.models.kpi

import java.time.{ ZoneOffset, OffsetDateTime }

import play.api.libs.json.Json
import com.particeep.api.core.Formatter

case class KpiValue(
    id:         String                 = "",
    created_at: Option[OffsetDateTime] = None,
    update_at:  Option[OffsetDateTime] = None,
    date:       OffsetDateTime         = OffsetDateTime.now(ZoneOffset.UTC),
    value:      Double                 = 0d,
    kpi_id:     Option[String]         = None,
    comment:    Option[String]         = None,
    tag:        Option[String]         = None
)

object KpiValue {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit val format = Json.format[KpiValue]
}
