package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.Gender.Gender
import com.particeep.api.models.enums.InvestorType.InvestorType
import play.api.libs.json.{ JsArray, JsObject }

import java.time.ZonedDateTime

case class UserEdition(
    first_name:                    Option[String]        = None,
    last_name:                     Option[String]        = None,
    maiden_name:                   Option[String]        = None,
    gender:                        Option[Gender]        = None,
    avatar_url:                    Option[String]        = None,
    birthday:                      Option[ZonedDateTime] = None,
    birth_place:                   Option[String]        = None,
    birth_country:                 Option[String]        = None,
    birth_department:              Option[String]        = None,
    residence_country:             Option[String]        = None,
    birth_cog:                     Option[String]        = None,
    phone:                         Option[String]        = None,
    nationality:                   Option[String]        = None,
    bio:                           Option[String]        = None,
    sector:                        Option[String]        = None,
    investor_type:                 Option[InvestorType]  = None,
    linkedin_url:                  Option[String]        = None,
    viadeo_url:                    Option[String]        = None,
    allow_mail_notifications:      Option[Boolean]       = None,
    does_pay_taxes:                Option[Boolean]       = None,
    subject_to_income_tax:         Option[Boolean]       = None,
    single_fixed_tax_base_1:       Option[Boolean]       = None,
    single_fixed_tax_base_2_and_3: Option[Boolean]       = None,
    profession:                    Option[String]        = None,
    business_line:                 Option[String]        = None,
    addresses:                     Option[Seq[Address]]  = None,
    tag:                           Option[String]        = None,
    is_pro:                        Option[Boolean]       = None,
    account_validation_status:     Option[String]        = None,
    company_business_name:         Option[String]        = None,
    siren:                         Option[String]        = None,
    siret:                         Option[String]        = None,
    rcs_registration_year:         Option[String]        = None,
    rcs_city:                      Option[String]        = None,
    legal_status:                  Option[String]        = None,
    tva_intra:                     Option[String]        = None,
    lang:                          Option[String]        = None,
    ips:                           Option[JsArray]       = None,
    creator_type:                  Option[String]        = None,
    creator_name:                  Option[String]        = None,
    is_id_doc_verified:            Option[Boolean]       = None,
    id_doc_check_date:             Option[ZonedDateTime] = None,
    id_doc_expiration_date:        Option[ZonedDateTime] = None,
    can_access:                    Option[Boolean]       = None,
    custom:                        Option[JsObject]      = None,
    patrimony:                     Option[UserPatrimony] = None
)

object UserEdition {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  implicit val format = Jsonx.formatCaseClass[UserEdition]
}
