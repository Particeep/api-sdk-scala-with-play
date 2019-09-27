package com.particeep.api.models.user

import java.time.ZonedDateTime

import ai.x.play.json.Jsonx
import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.Gender.Gender
import com.particeep.api.models.enums.InvestorType.InvestorType
import play.api.libs.json.JsObject

case class UserEdition(
  first_name:                Option[String]        = None,
  last_name:                 Option[String]        = None,
  maiden_name:               Option[String]        = None,
  gender:                    Option[Gender]        = None,
  avatar_url:                Option[String]        = None,
  birthday:                  Option[ZonedDateTime] = None,
  birth_place:               Option[String]        = None,
  birth_country:             Option[String]        = None,
  birth_department:          Option[String]        = None,
  birth_cog:                 Option[String]        = None,
  phone:                     Option[String]        = None,
  nationality:               Option[String]        = None,
  bio:                       Option[String]        = None,
  sector:                    Option[String]        = None,
  investor_type:             Option[InvestorType]  = None,
  linkedin_url:              Option[String]        = None,
  viadeo_url:                Option[String]        = None,
  allow_mail_notifications:  Option[Boolean]       = None,
  does_pay_taxes:            Option[Boolean]       = None,
  addresses:                 Option[Seq[Address]]  = None,
  tag:                       Option[String]        = None,
  tax_system:                Option[String]        = None,
  tax_rate:                  Option[Double]        = None,
  is_pro:                    Option[Boolean]       = None,
  account_validation_status: Option[String]        = None,
  custom:                    Option[JsObject]      = None
)

object UserEdition {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  implicit val format = Jsonx.formatCaseClass[UserEdition]
}
