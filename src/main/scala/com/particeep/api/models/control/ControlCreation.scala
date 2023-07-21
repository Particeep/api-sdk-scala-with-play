package com.particeep.api.models.control

import play.api.libs.json.{ Json, OFormat }

final case class ControlCreation(
    target_id:     String,
    target_type:   String,
    level:         Int,
    block_targets: Option[Map[String, List[String]]]
)

object ControlCreation {
  implicit val control_creation_format: OFormat[ControlCreation] = Json.format[ControlCreation]
}