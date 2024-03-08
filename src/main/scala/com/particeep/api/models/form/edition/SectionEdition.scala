package com.particeep.api.models.form.edition

import play.api.libs.json._

case class SectionEdition(
  name:        Option[Map[String, String]],
  description: Option[Map[String, String]],
  index:       Option[Int]
)

object SectionEdition {
  val format = Json.format[SectionEdition]
}
