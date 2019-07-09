package com.particeep.api.models.document

import play.api.libs.json.Json

case class InvestmentDocument(
  id:                     String          = "",
  created_by:             String          = "",
  fundraise_id:           String          = "",
  document_id:            String          = "",
  investor_target_type:   Option[String]  = None,
  signature_needed:       Option[Boolean] = None,
  cgu_must_sign:          Option[Boolean] = None,
  co_issuers_must_sign:   Option[Boolean] = None,
  additional_signatories: Option[String]  = None
)

object InvestmentDocument {
  val format = Json.format[InvestmentDocument]
}
