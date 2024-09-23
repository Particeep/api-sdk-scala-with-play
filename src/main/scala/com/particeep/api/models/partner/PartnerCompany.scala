package com.particeep.api.models.partner

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ JsObject, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.OriasStatus
import com.particeep.api.models.enums.RegulatoryStatus.RegulatoryStatus

case class PartnerCompany(
  id:                   String,
  created_at:           OffsetDateTime,
  user_id:              String,
  regulatory_status:    Option[RegulatoryStatus] = None,
  orias_status:         Option[OriasStatus]      = None,
  orias_number:         Option[String]           = None,
  regafi_number:        Option[String]           = None,
  geco_number:          Option[String]           = None,
  legal_representative: Option[String]           = None,
  unicia_id:            Option[String]           = None,
  tag:                  Option[String]           = None,
  custom:               Option[JsObject]         = None
)

object PartnerCompany {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[PartnerCompany]              = Jsonx.formatCaseClass[PartnerCompany]
}
