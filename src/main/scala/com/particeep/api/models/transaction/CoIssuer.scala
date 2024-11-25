package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, OFormat }

case class CoIssuer(
  user_id: String,
  role:    String
)

object CoIssuer {
  implicit val format: OFormat[CoIssuer] = Json.format[CoIssuer]
}
