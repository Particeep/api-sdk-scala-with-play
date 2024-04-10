package com.particeep.api.models.form.creation

import play.api.libs.json.{ JsObject, Json }

case class SectionCreation(
  form_id:     String,
  name:        Option[Map[String, String]] = None,
  description: Option[Map[String, String]] = None,
  index:       Option[Int]                 = None,
  tag:         Option[String]              = None,
  custom:      Option[JsObject]            = None
)

object SectionCreation {
  val format = Json.format[SectionCreation]
}
