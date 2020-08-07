package com.particeep.api.models.user

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.Gender.Gender
import com.particeep.api.models.enums.InvestorType.InvestorType
import org.cvogt.play.json.Jsonx
import play.api.libs.json.JsObject

case class UserCreation(
  email:                     String,
  password:                  Option[String]        = None,
  has_been_claimed:          Option[Boolean]       = None,
  first_name:                Option[String]        = None,
  last_name:                 Option[String]        = None,
  maiden_name:               Option[String]        = None,
  gender:                    Option[Gender]        = None,
  avatar_url:                Option[String]        = None,
  birthday:                  Option[ZonedDateTime] = None,
  birth_place:               Option[String]        = None,
  birth_country:             Option[String]        = None,
  birth_department:          Option[String]        = None,
  phone:                     Option[String]        = None,
  nationality:               Option[String]        = None,
  bio:                       Option[String]        = None,
  sector:                    Option[String]        = None,
  investor_type:             Option[InvestorType]  = None,
  linkedin_url:              Option[String]        = None,
  viadeo_url:                Option[String]        = None,
  allow_mail_notifications:  Option[Boolean]       = None,
  does_pay_taxes:            Option[Boolean]       = None,
  is_pro:                    Option[Boolean]       = None,
  account_validation_status: Option[String]        = None,
  lang:                      Option[String]        = None,
  addresses:                 Option[Seq[Address]]  = None,
  tag:                       Option[String]        = None,
  custom:                    Option[JsObject]      = None
)

object UserCreation {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Jsonx.formatCaseClass[UserCreation]
}
