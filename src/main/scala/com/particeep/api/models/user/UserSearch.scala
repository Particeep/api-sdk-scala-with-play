package com.particeep.api.models.user

import java.time.OffsetDateTime

case class UserSearch(
  created_after:     Option[OffsetDateTime] = None,
  created_before:    Option[OffsetDateTime] = None,
  gender:            Option[String]         = None,
  first_name:        Option[String]         = None,
  last_name:         Option[String]         = None,
  birthday:          Option[OffsetDateTime] = None,
  birth_place:       Option[String]         = None,
  birth_country:     Option[String]         = None,
  birth_department:  Option[String]         = None,
  phone:             Option[String]         = None,
  nationality:       Option[String]         = None,
  sector:            Option[String]         = None,
  investor_type:     Option[String]         = None,
  does_pay_taxes:    Option[Boolean]        = None,
  city:              Option[String]         = None,
  wallet_type:       Option[String]         = None,
  status:            Option[String]         = None,
  tag:               Option[String]         = None,
  roles:             Option[String]         = None,
  targeting_roles:   Option[String]         = None,
  ids:               Option[String]         = None,
  pro_qualification: Option[String]         = None
)

case class UserSearchAdditional(
  email:                                 Option[String] = None,
  account_validation_status:             Option[String] = None,
  partner_manager_id:                    Option[String] = None,
  partner_manager_company_business_name: Option[String] = None,
  company_business_name:                 Option[String] = None,
  siren:                                 Option[String] = None,
  siret:                                 Option[String] = None,
  rcs_registration_year:                 Option[String] = None,
  rcs_city:                              Option[String] = None,
  legal_status:                          Option[String] = None,
  tva_intra:                             Option[String] = None
)
