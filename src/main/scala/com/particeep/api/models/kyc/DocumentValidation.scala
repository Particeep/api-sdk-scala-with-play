package com.particeep.api.models.kyc

import com.particeep.api.models.enums.{ DocumentPart, DocumentType }
import play.api.libs.json.Json

case class DocumentValidation(
    url:           String               = "",
    document_type: Option[DocumentType] = None,
    part:          Option[DocumentPart] = None
)

object DocumentValidation {
  val format = Json.format[DocumentValidation]
}
