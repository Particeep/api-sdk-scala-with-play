package com.particeep.api.models.document_generation

import play.api.libs.json.{ JsValue, Json }

case class DocumentGenerationAndUpload(
    url:                    String,
    params_obj:             Option[Map[String, JsValue]] = None,
    params_str:             Option[Map[String, String]]  = None,
    name:                   Option[String]               = None,
    description:            Option[String]               = None,
    path:                   Option[String]               = None,
    override_existing_file: Option[Boolean]              = None,
    tag:                    Option[String]               = None
)

object DocumentGenerationAndUpload {
  val format = Json.format[DocumentGenerationAndUpload]
}
