package com.particeep.api.models.role

import play.api.libs.json.{ Json, OFormat }

case class RolesCreation(
  id:          String         = "",
  user_id:     String         = "",
  role_name:   String         = "",
  target_id:   Option[String] = None,
  target_type: Option[String] = None,
  tag:         Option[String] = None
)

object RolesCreation {
  val format: OFormat[RolesCreation] = Json.format[RolesCreation]
}
