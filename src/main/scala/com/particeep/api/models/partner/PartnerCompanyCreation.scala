package com.particeep.api.models.partner

import play.api.libs.json.{ JsObject, Json, OFormat }

import com.particeep.api.models.enums.{ OriasStatus, PartnerManagerFormTag, RegulatoryStatus }

case class PartnerCompanyCreation(
  company_business_name: Option[String]                             = None,
  siren:                 Option[String]                             = None,
  siret:                 Option[String]                             = None,
  rcs_registration_year: Option[String]                             = None,
  rcs_city:              Option[String]                             = None,
  tva_intra:             Option[String]                             = None,
  legal_status:          Option[String]                             = None,
  regulatory_status:     Option[RegulatoryStatus]                   = None,
  orias_status:          Option[OriasStatus]                        = None,
  orias_number:          Option[String]                             = None,
  regafi_number:         Option[String]                             = None,
  geco_number:           Option[String]                             = None,
  legal_representative:  Option[String]                             = None,
  forms:                 Option[Map[PartnerManagerFormTag, String]] = None,
  unicia_id:             Option[String]                             = None,
  tag:                   Option[String]                             = None,
  custom:                Option[JsObject]                           = None
)

object PartnerCompanyCreation {
  val format: OFormat[PartnerCompanyCreation] = Json.format[PartnerCompanyCreation]
}
