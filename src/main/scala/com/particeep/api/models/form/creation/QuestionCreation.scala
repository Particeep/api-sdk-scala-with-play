package com.particeep.api.models.form.creation

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.QuestionType

case class QuestionCreation(
  section_id:        String,
  label:             Option[Map[String, String]] = None,
  description:       Option[Map[String, String]] = None,
  question_type:     Option[QuestionType]        = None,
  required:          Option[Boolean]             = None,
  pattern:           Option[String]              = None,
  index:             Option[Int]                 = None,
  document_filename: Option[String]              = None,
  tag:               Option[String]              = None
)

object QuestionCreation {
  val format: OFormat[QuestionCreation] = Json.format[QuestionCreation]
}
