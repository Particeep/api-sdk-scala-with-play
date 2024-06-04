package com.particeep.api.models.signature

import play.api.libs.json.{ JsObject, Json, OFormat }

import com.particeep.api.models.enums.SignerType

case class SignatureCreation(
  language:       Option[String]   = None,
  document_id:    String,
  fileName:       String,
  firstName:      String,
  lastName:       String,
  email:          String,
  phone:          String,
  description:    Option[String]   = None,
  successURL:     Option[String]   = None,
  cancelURL:      Option[String]   = None,
  failURL:        Option[String]   = None,
  target_id:      Option[String]   = None,
  target_type:    Option[String]   = None,
  signature_type: Option[String]   = None,
  signer_type:    SignerType,
  tag:            Option[String]   = None,
  custom:         Option[JsObject] = None
)

object SignatureCreation {
  val format: OFormat[SignatureCreation] = Json.format[SignatureCreation]
}
