package com.particeep.api.models.control

import play.api.libs.json.{ Json, OFormat }

case class ControlBlocksUpdate(
  control_blocks_update: Seq[ControlBlockUpdate],
  author_email:          String
)

object ControlBlocksUpdate {
  implicit val control_blocks_update_format: OFormat[ControlBlocksUpdate] = Json.format[ControlBlocksUpdate]
}
