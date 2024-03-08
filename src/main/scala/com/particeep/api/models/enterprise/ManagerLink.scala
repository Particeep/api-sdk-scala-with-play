package com.particeep.api.models.enterprise

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class ManagerLink(
  id:         Option[String]         = None,
  created_at: Option[OffsetDateTime] = None,
  manager_id: String                 = "",
  name:       Option[String]         = None,
  tag:        Option[String]         = None
)

object ManagerLink {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[ManagerLink]
}
