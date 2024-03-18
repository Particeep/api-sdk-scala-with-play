package com.particeep.api.models.imports

import play.api.libs.json._
import com.particeep.api.models.enums.ImportType.ImportType
import com.particeep.api.models.enums.ImportStateStatus.ImportStateStatus
import java.time.OffsetDateTime
import com.particeep.api.core.Formatter

/**
 * Created by Noe on 10/04/2017.
 */
case class ImportResult[T](
    id:              String,
    created_at:      OffsetDateTime,
    updated_at:      Option[OffsetDateTime],
    status:          ImportStateStatus,
    import_type:     ImportType,
    line_processed:  Int,
    nb_created:      Int,
    nb_fail:         Int,
    line_with_error: List[LineError]        = List(),
    line_on_success: List[LineSuccess[T]]   = List(),
    tag:             Option[String]         = None,
    custom:          Option[JsObject]       = None
)

object ImportResult {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val line_error_format: OFormat[LineError] = LineError.format
  implicit def line_success_format[T](implicit fmt: Format[T]): Format[LineSuccess[T]] = LineSuccess.format[T]
  def format[T](implicit fmt: Format[T]): Format[ImportResult[T]] = new Format[ImportResult[T]] {
    def reads(json: JsValue): JsResult[ImportResult[T]] = JsSuccess(new ImportResult[T](
      (json \ "id").as[String],
      (json \ "created_at").as[OffsetDateTime],
      (json \ "updated_at").asOpt[OffsetDateTime],
      (json \ "status").as[ImportStateStatus],
      (json \ "import_type").as[ImportType],
      (json \ "line_processed").as[Int],
      (json \ "nb_created").as[Int],
      (json \ "nb_fail").as[Int],
      (json \ "line_with_error").as[List[LineError]],
      (json \ "line_on_success").as[List[LineSuccess[T]]],
      (json \ "tag").asOpt[String],
      (json \ "custom").asOpt[JsObject]
    ))
    def writes(ir: ImportResult[T]) = JsObject(Seq(
      "id" -> JsString(ir.id),
      "created_at" -> Json.toJson(ir.created_at),
      "updated_at" -> Json.toJson(ir.updated_at),
      "status" -> Json.toJson(ir.status),
      "import_type" -> Json.toJson(ir.import_type),
      "line_processed" -> JsNumber(ir.line_processed),
      "nb_created" -> JsNumber(ir.nb_created),
      "nb_fail" -> JsNumber(ir.nb_fail),
      "line_with_error" -> Json.toJson(ir.line_with_error),
      "line_on_success" -> Json.toJson(ir.line_on_success),
      "tag" -> Json.toJson(ir.tag),
      "custom" -> Json.toJson(ir.custom)
    ))
  }
}

