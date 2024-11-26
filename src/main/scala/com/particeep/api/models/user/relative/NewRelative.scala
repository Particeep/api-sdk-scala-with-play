package com.particeep.api.models.user.relative

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.user.User

case class NewRelative(
  user: User,
  role: RelativeRole
)

object NewRelative {
  private[this] implicit val user_format: OFormat[User] = User.format
  val format: OFormat[NewRelative]                      = Json.format[NewRelative]
}
