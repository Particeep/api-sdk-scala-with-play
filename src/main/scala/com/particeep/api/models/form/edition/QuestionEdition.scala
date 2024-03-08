package com.particeep.api.models.form.edition

import play.api.libs.json.Json

import com.particeep.api.models.enums.QuestionType.QuestionType

case class QuestionEdition(
    label:                 Option[Map[String, String]] = None,
    description:           Option[Map[String, String]] = None,
    possibility_id_dep:    Option[String]              = None,
    valid_possibility_ids: Option[String]              = None,
    question_type:         Option[QuestionType],
    required:              Option[Boolean],
    pattern:               Option[String]              = None,
    index:                 Option[Int],
    document_filename:     Option[String]              = None,
    tag:                   Option[String]              = None
)

object QuestionEdition {
  val format = Json.format[QuestionEdition]
}
