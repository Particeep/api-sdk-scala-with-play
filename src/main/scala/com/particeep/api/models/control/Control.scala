package com.particeep.api.models.control

import play.api.libs.json.{ JsObject, Json, OFormat }

import java.time.OffsetDateTime

import com.particeep.api.models.enums.ControlStatus

final case class Control(
  id:            String,
  created_at:    OffsetDateTime,
  created_by:    String,
  assigned_to:   String,
  level:         Int,
  target_id:     String,
  target_type:   String,
  target_entity: JsObject,
  blocks:        List[ControlBlock],
  status:        ControlStatus,
  comment:       Option[String],
  document_ids:  List[String]
)

object Control {
  implicit val controlFormat: OFormat[Control] = Json.format[Control]
}
