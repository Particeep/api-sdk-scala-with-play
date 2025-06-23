package com.particeep.api.models.imports

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class ImportResult[T](
  import_state:  ImportState,
  success_lines: List[LineSuccess[T]] = List(),
  error_lines:   List[LineError]      = List()
)

object ImportResult {
  implicit val date_format: Writes[OffsetDateTime]                                     = Formatter.OffsetDateTimeWrites
  implicit val import_state_format: OFormat[ImportState]                               = ImportState.format
  implicit val line_error_format: OFormat[LineError]                                   = LineError.format
  implicit def line_success_format[T](implicit fmt: Format[T]): Format[LineSuccess[T]] = LineSuccess.format[T]
  def format[T](implicit fmt: Format[T]): Format[ImportResult[T]]                      = new Format[ImportResult[T]] {
    def reads(json: JsValue): JsResult[ImportResult[T]] = JsSuccess(new ImportResult[T](
      (json \ "import_state").as[ImportState],
      (json \ "success_lines").as[List[LineSuccess[T]]],
      (json \ "error_lines").as[List[LineError]]
    ))
    def writes(ir: ImportResult[T]): JsValue            = JsObject(Seq(
      "import_state"  -> Json.toJson(ir.import_state),
      "success_lines" -> Json.toJson(ir.success_lines),
      "error_lines"   -> Json.toJson(ir.error_lines)
    ))
  }
}
