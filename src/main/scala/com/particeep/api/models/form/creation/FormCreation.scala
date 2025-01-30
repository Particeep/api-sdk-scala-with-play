package com.particeep.api.models.form.creation

import play.api.libs.json.{ JsObject, Json, OFormat }

case class FormCreation(
  name:        Option[String],
  description: Option[String]       = None,
  tag:         Option[List[String]] = None,
  custom:      Option[JsObject]     = None
)

object FormCreation {
  val format: OFormat[FormCreation] = Json.format[FormCreation]
}
