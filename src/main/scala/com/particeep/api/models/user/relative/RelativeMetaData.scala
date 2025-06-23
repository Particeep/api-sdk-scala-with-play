package com.particeep.api.models.user.relative

import play.api.libs.json.{ Json, OFormat }

case class RelativeMetaData(
  user_id: String,
  role:    RelativeRole
)

object RelativeMetaData {
  implicit val format: OFormat[RelativeMetaData] = Json.format[RelativeMetaData]
}
