package com.particeep.api.models.form.get

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.QuestionType.QuestionType
import play.api.libs.json.{ Json, Writes }

case class Question(
    id:                    String                      = "",
    created_at:            Option[OffsetDateTime]      = None,
    section_id:            String                      = "",
    label:                 Option[Map[String, String]] = None,
    description:           Option[Map[String, String]] = None,
    possibility_id_dep:    Option[String]              = None,
    valid_possibility_ids: Option[String]              = None,
    question_type:         Option[QuestionType]        = None,
    pattern:               Option[String]              = None,
    required:              Boolean                     = false,
    document_filename:     Option[String]              = None,
    index:                 Option[Int]                 = None

)

object Question {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[Question]
}
