package com.particeep.api.models.kyc

import play.api.libs.json.Json

case class KycValidation(
    is_valid:         Boolean                = false,
    external_id:      String                 = "",
    service_name:     String                 = "",
    service_response: DocumentReportResponse
)

object KycValidation {
  implicit val drr_format = DocumentReportResponse.format
  val format = Json.format[KycValidation]
}
