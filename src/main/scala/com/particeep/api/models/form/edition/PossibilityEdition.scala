package com.particeep.api.models.form.edition

import play.api.libs.json.{Json, OFormat}

case class PossibilityEdition(
  label:  Option[Map[String, String]],
  index:  Option[Int],
  weight: Option[Int]
)

object PossibilityEdition {
  val format: OFormat[PossibilityEdition] = Json.format[PossibilityEdition]
}
