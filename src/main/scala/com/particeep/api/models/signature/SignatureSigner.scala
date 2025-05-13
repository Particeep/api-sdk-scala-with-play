package com.particeep.api.models.signature

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.SignerType

case class SignatureSigner(
  firstName:                String,
  lastName:                 String,
  email:                    String,
  phone:                    String,
  signer_type:              Option[SignerType] = None,
  document_id_for_position: Option[String],
  successURL:               Option[String]        = None,
  cancelURL:                Option[String]        = None,
  failURL:                  Option[String]        = None
)

object SignatureSigner {
  val format: OFormat[SignatureSigner] = Json.format[SignatureSigner]
}
