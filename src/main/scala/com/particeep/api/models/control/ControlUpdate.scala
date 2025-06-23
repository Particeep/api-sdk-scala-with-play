package com.particeep.api.models.control

import play.api.libs.json.{ Json, OFormat }

final case class ControlUpdate(
  comment:      Option[String]       = None,
  document_ids: Option[List[String]] = None,
  author_email: String
)

object ControlUpdate {
  implicit val control_update_format: OFormat[ControlUpdate] = Json.format[ControlUpdate]
}
