package com.particeep.api.models.signature

import play.api.libs.json.{ JsObject, Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.SignatureStatus.SignatureStatus

case class Signature(
  id:             String                  = "",
  created_at:     Option[OffsetDateTime]  = None,
  language:       String,
  fileUrl:        String,
  fileToSignUrl:  Option[String]          = None,
  fileName:       String,
  status:         Option[SignatureStatus] = None,
  external_id:    Option[String]          = None,
  firstName:      String,
  lastName:       String,
  email:          String,
  phone:          String,
  description:    Option[String]          = None,
  signedFileUrl:  Option[String]          = None,
  signature_type: Option[String]          = None,
  target_id:      Option[String]          = None,
  target_type:    Option[String]          = None,
  group_id:       Option[String]          = None,
  tag:            Option[String]          = None,
  custom:         Option[JsObject]        = None
)

object Signature {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[Signature]
}
