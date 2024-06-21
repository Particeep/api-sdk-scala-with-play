package com.particeep.api.models.control

import play.api.libs.json.{ Json, OFormat }

import java.time.OffsetDateTime

import com.particeep.api.models.enums.ControlStatus

final case class ControlView(
  id:           String,
  created_at:   OffsetDateTime,
  updated_at:   OffsetDateTime,
  deleted_at:   Option[OffsetDateTime],
  created_by:   String,
  assigned_to:  String,
  level:        Int,
  target_id:    String,
  target_type:  String,
  blocks:       List[ControlBlock],
  status:       ControlStatus,
  comment:      Option[String],
  document_ids: List[String] = List()
)

object ControlView {
  implicit val control_view_format: OFormat[ControlView] = Json.format[ControlView]
}

final case class ControlViewSearchCriteria(
  created_after:  Option[OffsetDateTime]      = None,
  created_before: Option[OffsetDateTime]      = None,
  level:          Option[Int]                 = None,
  target_id:      Option[String]              = None,
  target_type:    Option[String]              = None,
  status:         Option[List[ControlStatus]] = None,
  assigned_to:    Option[String]              = None,
  ids:            Option[String]              = None
)
