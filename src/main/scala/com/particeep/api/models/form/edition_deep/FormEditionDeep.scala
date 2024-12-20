package com.particeep.api.models.form.edition_deep

import play.api.libs.json.{ Json, OFormat }

case class FormEditionDeep(
  name:        Option[String],
  description: Option[String],
  tag:         Option[List[String]],
  sections:    Option[Seq[SectionEditionDeep]]
)

object FormEditionDeep {
  implicit val section_edition_format: OFormat[SectionEditionDeep] = SectionEditionDeep.format
  val format: OFormat[FormEditionDeep]                             = Json.format[FormEditionDeep]
}
