package com.particeep.api.models.user
import play.api.libs.json._

case class UserUpdateEmailOption(
  new_email: String
)

object UserUpdateEmailOption {
  implicit val format: OFormat[UserUpdateEmailOption] = Json.format[UserUpdateEmailOption]
}
