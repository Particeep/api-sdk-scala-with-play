package com.particeep.api.models.role

import play.api.libs.json.Json

case class GlobalRoleOption(
    role_name: Option[String] = None,
    tag:       Option[String] = None
)

object GlobalRoleOption {
  val format = Json.format[GlobalRoleOption]
}
