package com.particeep.api.models.signature

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.SignatureStatus.SignatureStatus

case class SignatureMultiple(
  id:             String,
  status:         SignatureStatus,
  current_signer: Int,
  signatures:     Seq[Signature]
)

object SignatureMultiple {
  implicit val signatureFormat: OFormat[Signature] = Signature.format
  val format                                       = Json.format[SignatureMultiple]
}
