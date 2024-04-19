package com.particeep.api.models.role

import play.api.libs.json.{Json, OFormat}

case class RoleCreation(
  target_id:   Option[String] = None,
  target_type: Option[String] = None,
  tag:         Option[String] = None
)

object RoleCreation {
  val format: OFormat[RoleCreation] = Json.format[RoleCreation]
}
