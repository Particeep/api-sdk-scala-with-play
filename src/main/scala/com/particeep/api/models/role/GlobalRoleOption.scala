package com.particeep.api.models.role

import play.api.libs.json.{Json, OFormat}

case class GlobalRoleOption(
  role_name: Option[String] = None,
  tag:       Option[String] = None
)

object GlobalRoleOption {
  val format: OFormat[GlobalRoleOption] = Json.format[GlobalRoleOption]
}
