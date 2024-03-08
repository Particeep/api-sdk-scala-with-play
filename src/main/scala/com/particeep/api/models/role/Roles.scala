package com.particeep.api.models.role

import play.api.libs.json._

case class Roles(
  user_id: String     = "",
  roles:   List[Role] = List()
)

object Roles {
  implicit val role_format: OFormat[Role] = Role.format

  val format = Json.format[Roles]
}
