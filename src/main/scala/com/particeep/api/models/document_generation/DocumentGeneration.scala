package com.particeep.api.models.document_generation

import play.api.libs.json.{ JsValue, Json }

case class DocumentGeneration(
    document_id: String,
    params_obj:  Option[Map[String, JsValue]],
    params_str:  Option[Map[String, String]]
)

object DocumentGeneration {
  val format = Json.format[DocumentGeneration]
}
