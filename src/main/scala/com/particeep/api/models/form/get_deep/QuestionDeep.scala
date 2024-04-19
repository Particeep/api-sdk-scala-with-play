package com.particeep.api.models.form.get_deep

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.QuestionType.QuestionType
import com.particeep.api.models.form.get.Possibility

case class QuestionDeep(
  id:                    String                      = "",
  created_at:            Option[OffsetDateTime]      = None,
  section_id:            String                      = "",
  label:                 Option[Map[String, String]] = None,
  description:           Option[Map[String, String]] = None,
  possibility_id_dep:    Option[String]              = None,
  valid_possibility_ids: Option[String]              = None,
  question_type:         Option[QuestionType]        = None,
  required:              Boolean                     = false,
  pattern:               Option[String]              = None,
  index:                 Option[Int]                 = None,
  possibilities:         Seq[Possibility]            = Seq(),
  tag:                   Option[String]              = None,

  // Only when getting a Form for a User
  answers:           Option[Seq[String]] = None,
  document_filename: Option[String]      = None
)

object QuestionDeep {
  implicit val date_format: Writes[OffsetDateTime]      = Formatter.OffsetDateTimeWrites
  implicit val possibility_format: OFormat[Possibility] = Possibility.format
  val format: OFormat[QuestionDeep] = Json.format[QuestionDeep]
}
