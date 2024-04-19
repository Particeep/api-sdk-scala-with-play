package com.particeep.api.models.form.creation

import play.api.libs.json.{Json, OFormat}

case class PossibilityCreation(
  question_id: String,
  label:       Option[Map[String, String]] = None,
  index:       Option[Int]                 = None,
  weight:      Option[Int]                 = None
)

object PossibilityCreation {
  val format: OFormat[PossibilityCreation] = Json.format[PossibilityCreation]
}
