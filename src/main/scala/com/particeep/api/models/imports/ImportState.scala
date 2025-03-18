package com.particeep.api.models.imports

import play.api.libs.json.{ JsObject, Json, OFormat }

import java.time.OffsetDateTime

import com.particeep.api.models.enums.{ ImportStateStatus, ImportType }

case class ImportState(
  id:             String,
  created_at:     OffsetDateTime,
  updated_at:     Option[OffsetDateTime],
  status:         ImportStateStatus,
  import_type:    ImportType,
  line_processed: Int,
  nb_created:     Int,
  nb_fail:        Int,
  tag:            Option[String]   = None,
  custom:         Option[JsObject] = None
)

object ImportState {
  val format: OFormat[ImportState] = Json.format[ImportState]
}
