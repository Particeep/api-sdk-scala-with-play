package com.particeep.api.models.signature

import play.api.libs.json.{ JsObject, Json, OFormat }

import java.util.UUID

case class SignatureMultipleCreation(
    language:       Option[String]       = None,
    documents_ids:  Seq[UUID],
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
  implicit val signatureSignerFormat: OFormat[SignatureSigner] = SignatureSigner.format
  val format = Json.format[SignatureMultipleCreation]
}
