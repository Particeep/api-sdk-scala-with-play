package com.particeep.api.models.form.edition

import play.api.libs.json.{Json, OFormat}

case class SectionEdition(
  name:        Option[Map[String, String]],
  description: Option[Map[String, String]],
  index:       Option[Int]
)

object SectionEdition {
  val format: OFormat[SectionEdition] = Json.format[SectionEdition]
}
