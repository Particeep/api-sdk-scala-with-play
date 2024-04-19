package com.particeep.api.models.form.edition_deep

import play.api.libs.json.{ Json, OFormat }

case class SectionEditionDeep(
  id:          Option[String],
  name:        Option[Map[String, String]],
  description: Option[Map[String, String]],
  index:       Option[Int],
  questions:   Option[Seq[QuestionEditionDeep]]
)

object SectionEditionDeep {
  implicit val question_edition_format: OFormat[QuestionEditionDeep] = QuestionEditionDeep.format
  val format: OFormat[SectionEditionDeep] = Json.format[SectionEditionDeep]
}
