package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ Format, JsArray, JsObject, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.OrganizationRole
import com.particeep.api.models.user.organization.Organization
import com.particeep.api.models.user.relative.RelativeMetaData
import com.particeep.api.models.wallet.BankAccount

case class UserData(
  id:                                    String                        = "",
  created_at:                            Option[OffsetDateTime]        = None,
  created_by:                            Option[String]                = None,
  email:                                 String                        = "",
  allow_mail_notifications:              Option[Boolean]               = None,
  gender:                                Option[String]                = None,
  first_name:                            Option[String]                = None,
  last_name:                             Option[String]                = None,
  use_name:                              Option[String]                = None,
  birthday:                              Option[OffsetDateTime]        = None,
  birth_place:                           Option[String]                = None,
  birth_country:                         Option[String]                = None,
  birth_department:                      Option[String]                = None,
  residence_country:                     Option[String]                = None,
  family_status:                         Option[String]                = None,
  matrimonial_regime:                    Option[String]                = None,
  phone:                                 Option[String]                = None,
  nationality:                           Option[String]                = None,
  sector:                                Option[String]                = None,
  investor_type:                         Option[String]                = None,
  does_pay_taxes:                        Option[Boolean]               = None,
  subject_to_income_tax:                 Option[Boolean]               = None,
  has_been_claimed:                      Option[Boolean]               = Some(true),
  profession:                            Option[String]                = None,
  business_line:                         Option[String]                = None,
  city:                                  Option[String]                = None,
  wallet_id:                             Option[String]                = None,
  wallet_updated_at:                     Option[OffsetDateTime]        = None,
  wallet_type:                           Option[String]                = None,
  wallet_status:                         Option[String]                = None,
  roles:                                 List[String]                  = List.empty,
  targeting_roles:                       Option[String]                = None,
  partner_manager_id:                    Option[String]                = None,
  partner_manager_company_business_name: Option[String]                = None,
  investor_score:                        Option[Long]                  = None,
  risk:                                  Option[String]                = None,
  tag:                                   Option[String]                = None,
  pro_qualification:                     Option[String]                = None,
  account_validation_status:             Option[String]                = None,
  lang:                                  Option[String]                = None,
  relatives:                             Option[Seq[Relative]]         = None,
  new_relatives:                         Option[Seq[RelativeMetaData]] = None,
  bankaccounts:                          Option[Seq[BankAccount]]      = None,
  ips:                                   Option[JsArray]               = None,
  is_id_doc_verified:                    Option[Boolean]               = None,
  id_doc_check_date:                     Option[OffsetDateTime]        = None,
  id_doc_expiration_date:                Option[OffsetDateTime]        = None,
  can_access:                            Option[Boolean]               = None,
  custom:                                Option[JsObject]              = None,
  company_business_name:                 Option[String]                = None,
  siren:                                 Option[String]                = None,
  siret:                                 Option[String]                = None,
  rcs_registration_year:                 Option[String]                = None,
  rcs_city:                              Option[String]                = None,
  legal_status:                          Option[String]                = None,
  tva_intra:                             Option[String]                = None,
  is_locked:                             Option[String]                = None,
  addresses:                             Option[Seq[Address]]          = None,
  organization_id:                       Option[String]                = None,
  organization_role:                     Option[OrganizationRole]      = None,
  birth_cog:                             Option[String]                = None,
  is_rcs_verified:                       Option[Boolean]               = None,
  creator_type:                          Option[String]                = None,
  creator_name:                          Option[String]                = None
)

object UserData {
  implicit val date_format: Writes[OffsetDateTime]                = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address]                    = Address.format
  implicit val relative_format: OFormat[Relative]                 = Relative.format
  implicit val relative_metadata_format: Format[RelativeMetaData] = RelativeMetaData.format
  implicit val organization_format: Format[Organization]          = Organization.format
  implicit val bankaccount_format: OFormat[BankAccount]           = BankAccount.format
  val format: OFormat[UserData]                                   = Jsonx.formatCaseClass[UserData]
}
