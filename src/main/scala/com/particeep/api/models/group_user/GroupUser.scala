package com.particeep.api.models.group_user

import java.time.ZonedDateTime

import com.particeep.api.models.user.User
import play.api.libs.json.Json

case class GroupUser(
  id:         String                = "",
  created_at: Option[ZonedDateTime] = None,
  name:       String,
  user_id:    String,
  user:       Option[User]          = None
)

object GroupUser {
  val format = Json.format[GroupUser]
}
