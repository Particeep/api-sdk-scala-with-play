package com.particeep.api.models.signature

import play.api.libs.json.{ JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.SignatureStatus.SignatureStatus
import com.particeep.api.models.enums.SignerType

case class Signature(
  id:                       String                  = "",
  created_at:               Option[OffsetDateTime]  = None,
  language:                 String,
  doc_id:                   String,
  fileToSignUrl:            Option[String]          = None,
  fileName:                 String,
  status:                   Option[SignatureStatus] = None,
  external_id:              Option[String]          = None,
  firstName:                String,
  lastName:                 String,
  email:                    String,
  phone:                    String,
  description:              Option[String]          = None,
  signed_doc_id:            Option[String]          = None,
  signature_type:           Option[String]          = None,
  target_id:                Option[String]          = None,
  target_type:              Option[String]          = None,
  group_id:                 Option[String]          = None,
  signer_type:              Option[SignerType]      = None,
  document_id_for_position: Option[String],
  tag:                      Option[String]          = None,
  custom:                   Option[JsObject]        = None
)

object Signature {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[Signature]                   = Json.format[Signature]
}
