package com.particeep.api.models.form.edition_deep

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.QuestionType.QuestionType
import com.particeep.api.models.form.edition_deep

case class QuestionEditionDeep(
  id:                    Option[String],
  label:                 Option[Map[String, String]] = None,
  description:           Option[Map[String, String]] = None,
  possibility_id_dep:    Option[String]              = None,
  valid_possibility_ids: Option[String]              = None,
  question_type:         Option[QuestionType],
  required:              Option[Boolean],
  index:                 Option[Int],
  pattern:               Option[String]              = None,
  possibilities:         Option[Seq[edition_deep.PossibilityEditionDeep]],
  document_filename:     Option[String]              = None,
  tag:                   Option[String]              = None
)

object QuestionEditionDeep {
  implicit val possibility_edition_format: OFormat[PossibilityEditionDeep] = edition_deep.PossibilityEditionDeep.format
  val format                                                               = Json.format[QuestionEditionDeep]
}
