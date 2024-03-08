package com.particeep.api.models.signature

import play.api.libs.json._

case class SignatureDataMultiple(
  id:             String,
  status:         String,
  current_signer: Int,
  signatures:     Seq[SignatureData]
)

object SignatureDataMultiple {
  implicit val signatureDataFormat: OFormat[SignatureData] = SignatureData.format
  implicit val format: OFormat[SignatureDataMultiple]      = Json.format[SignatureDataMultiple]
}
