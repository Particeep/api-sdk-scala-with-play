package com.particeep.api.models.user

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.{ JsObject, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class OrganizationOption(
  company_business_name:    Option[String]   = None,
  siren:                    Option[String]   = None,
  siret:                    Option[String]   = None,
  is_rcs_verified:          Option[Boolean]  = None,
  rcs_registration_year:    Option[String]   = None,
  rcs_city:                 Option[String]   = None,
  legal_status:             Option[String]   = None,
  tva_intra:                Option[String]   = None,
  patrimony_exploit_result: Option[Long]     = None,
  tag:                      Option[String]   = None,
  custom:                   Option[JsObject] = None
)

object OrganizationOption {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[OrganizationOption]          = Jsonx.formatCaseClass[OrganizationOption]
}
