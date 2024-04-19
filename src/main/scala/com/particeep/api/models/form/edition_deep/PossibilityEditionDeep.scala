package com.particeep.api.models.form.edition_deep

import play.api.libs.json.{Json, OFormat}

case class PossibilityEditionDeep(
  id:     Option[String],
  label:  Option[Map[String, String]],
  index:  Option[Int],
  weight: Option[Int]
)

object PossibilityEditionDeep {
  val format: OFormat[PossibilityEditionDeep] = Json.format[PossibilityEditionDeep]
}
