package com.particeep.api.models.group_user

import play.api.libs.json.Json

case class GroupUserOption(
  name:    String,
  user_id: String
)

object GroupUserOption {
  val format = Json.format[GroupUserOption]
}
