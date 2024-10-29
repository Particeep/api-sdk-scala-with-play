package com.particeep.api.models.user.relative

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.{Gender, ProQualification}

case class NewRelative(
  id:                        String                   = "",
  email:                     String,
  role: RelativeRole,
  parent_id: String,
  created_at:                Option[OffsetDateTime]   = None,
  created_by:                Option[String]           = None,
  first_name:                Option[String]           = None,
  last_name:                 Option[String]           = None,
  use_name:                  Option[String]           = None,
  gender:                    Option[Gender]           = None,
  birthday:                  Option[OffsetDateTime]   = None,
  birth_place:               Option[String]           = None,
  birth_country:             Option[String]           = None,
  birth_department:          Option[String]           = None,
  residence_country:         Option[String]           = None,
  family_status:             Option[String]           = None,
  matrimonial_regime:        Option[String]           = None,
  birth_cog:                 Option[String]           = None,
  phone:                     Option[String]           = None,
  nationality:               Option[String]           = None,
  allow_mail_notifications:  Option[Boolean]          = None,
  does_pay_taxes:            Option[Boolean]          = None,
  subject_to_income_tax:     Option[Boolean]          = None,
  profession:                Option[String]           = None,
  business_line:             Option[String]           = None,
  addresses:                 Option[Seq[Address]]     = None,
  tag:                       Option[String]           = None,
  investor_score:            Option[Long]             = None,
  risk:                      Option[String]           = None,
  pro_qualification:         Option[ProQualification] = None,
  account_validation_status: Option[String]           = None,
  lang:                      Option[String]           = None,
  ips:                       Option[JsArray]          = None,
  creator_type:              Option[String]           = None,
  creator_name:              Option[String]           = None,
  is_id_doc_verified:        Option[Boolean]          = None,
  id_doc_check_date:         Option[OffsetDateTime]   = None,
  id_doc_expiration_date:    Option[OffsetDateTime]   = None,
  custom:                    Option[JsObject]         = None
)

object NewRelative {
  implicit val date_format: Writes[OffsetDateTime]           = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address]               = Address.format
  val format: OFormat[NewRelative]                                  = Jsonx.formatCaseClass[NewRelative]
}
