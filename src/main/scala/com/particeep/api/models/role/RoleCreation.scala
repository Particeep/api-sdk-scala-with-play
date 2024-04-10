package com.particeep.api.models.role

import play.api.libs.json.Json

case class RoleCreation(
  target_id:   Option[String] = None,
  target_type: Option[String] = None,
  tag:         Option[String] = None
)

object RoleCreation {
  val format = Json.format[RoleCreation]
}
