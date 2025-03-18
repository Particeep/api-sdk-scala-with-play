package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.user.relative.RelativeRole

case class CoIssuer(
  user_id: String,
  role:    Option[RelativeRole] = None
)

object CoIssuer {
  implicit val format: OFormat[CoIssuer] = Json.format[CoIssuer]
}
