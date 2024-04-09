package com.particeep.api.models.partner

import play.api.libs.json.{ JsObject, Json }

import com.particeep.api.models.enums.OriasStatus.OriasStatus
import com.particeep.api.models.enums.RegulatoryStatus.RegulatoryStatus

case class PartnerCompanyCreation(
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

object PartnerCompanyCreation {
  val format = Json.format[PartnerCompanyCreation]
}
