package com.particeep.api.models.control

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.ControlBlockStatus

final case class ControlBlockUpdate(
  id:      String,
  doc_ids: Seq[String]                = Seq.empty,
  status:  Option[ControlBlockStatus] = None,
  comment: Option[String]             = None
)

object ControlBlockUpdate {
  implicit val control_blocks_update_format: OFormat[ControlBlockUpdate] = Json.format[ControlBlockUpdate]
}
