package com.particeep.api.models.enterprise

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class ManagerLink(
    id:         Option[String]         = None,
    created_at: Option[OffsetDateTime] = None,
    manager_id: String                 = "",
    name:       Option[String]         = None,
    tag:        Option[String]         = None
)

object ManagerLink {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[ManagerLink]
}
