package com.particeep.api.models.signature

import play.api.libs.json.{ JsObject, Json }

case class SignatureMultipleCreation(
  language:       Option[String]       = None,
  fileUrl:        String,
  fileName:       String,
  description:    Option[String]       = None,
  signers:        Seq[SignatureSigner],
  target_id:      Option[String]       = None,
  target_type:    Option[String]       = None,
  signature_type: Option[String]       = None,
  tag:            Option[String]       = None,
  custom:         Option[JsObject]     = None
)

object SignatureMultipleCreation {
  private[this] implicit val signatureSignerFormat = SignatureSigner.format
  val format = Json.format[SignatureMultipleCreation]
}
