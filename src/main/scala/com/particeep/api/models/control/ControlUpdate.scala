package com.particeep.api.models.control

import play.api.libs.json.{ Json, OFormat }

final case class ControlUpdate(
  comment:         Option[String]      = None,
  document_id_seq: Option[Seq[String]] = None
)

object ControlUpdate {
  implicit val control_update_format: OFormat[ControlUpdate] = Json.format[ControlUpdate]
}
