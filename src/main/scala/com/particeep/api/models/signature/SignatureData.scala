package com.particeep.api.models.signature

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import play.api.libs.json.{ JsObject, Writes }

case class SignatureData(
    id:             String,
    created_at:     Option[OffsetDateTime] = None,
    language:       Option[String]         = None,
    fileUrl:        Option[String]         = None,
    fileToSignUrl:  Option[String]         = None,
    signedFileUrl:  Option[String]         = None,
    fileName:       Option[String]         = None,
    status:         Option[String]         = None,
    external_id:    Option[String]         = None,
    firstName:      Option[String]         = None,
    lastName:       Option[String]         = None,
    email:          Option[String]         = None,
    phone:          Option[String]         = None,
    description:    Option[String]         = None,
    target_id:      Option[String]         = None,
    target_type:    Option[String]         = None,
    signer_order:   Option[Int]            = None,
    total_signer:   Option[Int]            = None,
    group_id:       Option[String]         = None,
    partner_ids:    Option[String]         = None,
    fundraise_id:   Option[String]         = None,
    fundraise_type: Option[String]         = None,
    fundraise_name: Option[String]         = None,
    signature_type: Option[String]         = None,
    tag:            Option[String]         = None,
    custom:         Option[JsObject]       = None
)

object SignatureData {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Jsonx.formatCaseClass[SignatureData]
}
