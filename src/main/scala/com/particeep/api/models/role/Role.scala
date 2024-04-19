package com.particeep.api.models.role

import play.api.libs.json.{Json, OFormat, Writes}

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter

case class Role(
  id:          String                 = "",
  created_at:  Option[OffsetDateTime] = None,
  role_name:   String                 = "",
  target_id:   Option[String]         = None,
  target_type: Option[String]         = None,
  tag:         Option[String]         = None
)

object Role {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[Role] = Json.format[Role]
}
