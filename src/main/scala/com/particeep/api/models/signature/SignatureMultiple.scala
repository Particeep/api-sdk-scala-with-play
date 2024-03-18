package com.particeep.api.models.signature

import com.particeep.api.models.enums.SignatureStatus.SignatureStatus
import play.api.libs.json.{ Json, OFormat }

case class SignatureMultiple(
    id:             String,
    status:         SignatureStatus,
    current_signer: Int,
    signatures:     Seq[Signature]
)

object SignatureMultiple {
  implicit val signatureFormat: OFormat[Signature] = Signature.format
  val format = Json.format[SignatureMultiple]
}
