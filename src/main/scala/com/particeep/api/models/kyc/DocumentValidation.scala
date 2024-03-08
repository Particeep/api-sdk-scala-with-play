package com.particeep.api.models.kyc

import play.api.libs.json.{ JsValue, Json }

import com.particeep.api.models.enums.{ DocumentPart, DocumentType }

case class DocumentValidation(
  url:                 String               = "",
  document_type:       Option[DocumentType] = None,
  part:                Option[DocumentPart] = None,
  document_input_data: JsValue              = Json.obj()
)

object DocumentValidation {
  val format = Json.format[DocumentValidation]
}
