package com.particeep.api.models.partner

import play.api.libs.json.{ JsObject, JsString, Json, OFormat, Writes }

import com.particeep.api.models.enums.{ OriasStatus, PartnerManagerFormTag, RegulatoryStatus }

case class PartnerCompanyEdition(
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

object PartnerCompanyEdition {
  implicit val forms_writes: Writes[Map[PartnerManagerFormTag, String]] = Writes { value =>
    JsObject(value.map { case (key, value) => key.toString -> JsString(value) })
  }
  val format: OFormat[PartnerCompanyEdition]                            = Json.format[PartnerCompanyEdition]
}
