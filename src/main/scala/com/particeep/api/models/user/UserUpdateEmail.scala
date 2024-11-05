package com.particeep.api.models.user
import play.api.libs.json._

case class UserUpdateEmail(
  new_email: String
)

object UserUpdateEmail {
  implicit val format: OFormat[UserUpdateEmail] = Json.format[UserUpdateEmail]
}
