package com.particeep.api.models.user

import java.time.ZonedDateTime

case class UserSearch(
  created_after:    Option[ZonedDateTime] = None,
  created_before:   Option[ZonedDateTime] = None,
  gender:           Option[String]        = None,
  first_name:       Option[String]        = None,
  last_name:        Option[String]        = None,
  birthday:         Option[ZonedDateTime] = None,
  birth_place:      Option[String]        = None,
  birth_country:    Option[String]        = None,
  birth_department: Option[String]        = None,
  phone:            Option[String]        = None,
  nationality:      Option[String]        = None,
  sector:           Option[String]        = None,
  investor_type:    Option[String]        = None,
  does_pay_taxes:   Option[Boolean]       = None,
  city:             Option[String]        = None,
  wallet_type:      Option[String]        = None,
  status:           Option[String]        = None,
  tag:              Option[String]        = None,
  roles:            Option[String]        = None,
  targeting_roles:  Option[String]        = None,
  ids:              Option[String]        = None,
  is_pro:           Option[Boolean]       = None
)

case class UserSearchAdditional(
  account_validation_status: Option[String] = None,
  partner_manager_id:        Option[String] = None
)
