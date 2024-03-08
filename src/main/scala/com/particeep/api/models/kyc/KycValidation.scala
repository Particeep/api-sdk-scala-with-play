package com.particeep.api.models.kyc

import play.api.libs.json._

case class KycValidation(
  is_valid:         Boolean = false,
  external_id:      String  = "",
  service_name:     String  = "",
  service_response: DocumentValidationResponse
)

object KycValidation {
  implicit val drr_format: OFormat[DocumentValidationResponse] = DocumentValidationResponse.format
  val format                                                   = Json.format[KycValidation]
}
