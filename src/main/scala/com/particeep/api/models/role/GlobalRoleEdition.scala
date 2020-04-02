package com.particeep.api.models.role

import play.api.libs.json.Json

case class GlobalRoleEdition(
  id:          String         = "",
  role_name:   Option[String] = None,
  target_id:   Option[String] = None,
  target_type: Option[String] = None,
  tag:         Option[String] = None
)

object GlobalRoleEdition {
  val format = Json.format[GlobalRoleEdition]
}
