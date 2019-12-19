package com.particeep.api.models.document_generation

import play.api.libs.json.{ JsValue, Json }

case class DocumentGenerationAndUpload(
  url:         String,
  params_obj:  Option[Map[String, JsValue]],
  params_str:  Option[Map[String, String]],
  name:        Option[String],
  description: Option[String],
  path:        Option[String],
  override_existing_file: Option[Boolean]
)

object DocumentGenerationAndUpload {
  val format = Json.format[DocumentGenerationAndUpload]
}
