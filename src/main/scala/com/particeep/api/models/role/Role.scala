package com.particeep.api.models.role

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

/**
 * Created by Noe on 19/01/2017.
 */
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
  val format                                       = Json.format[Role]
}
