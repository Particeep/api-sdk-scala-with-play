package com.particeep.api.models.kpi

import java.time.OffsetDateTime
import play.api.libs.json.{ Json, JsObject }
import com.particeep.api.core.Formatter

case class Kpi(
    id:          String                 = "",
    created_at:  Option[OffsetDateTime] = None,
    name:        String                 = "",
    user_id:     Option[String]         = None,
    target_id:   Option[String]         = None,
    target_type: Option[String]         = None,
    tag:         Option[String]         = None,
    custom:      Option[JsObject]       = None,
    values:      List[KpiValue]         = List()
)

object Kpi {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[Kpi]
}
