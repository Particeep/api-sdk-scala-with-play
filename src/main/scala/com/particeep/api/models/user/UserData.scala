package com.particeep.api.models.user

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.wallet.BankAccount
import org.cvogt.play.json.Jsonx
import play.api.libs.json.{ JsObject, Json }

/**
 * Created by Noe on 18/05/2017.
 */
case class UserData(
  id:                        String                   = "",
  created_at:                Option[ZonedDateTime]    = None,
  email:                     String                   = "",
  allow_mail_notifications:  Option[Boolean]          = None,
  gender:                    Option[String]           = None,
  first_name:                Option[String]           = None,
  last_name:                 Option[String]           = None,
  maiden_name:               Option[String]           = None,
  avatar_url:                Option[String]           = None,
  birthday:                  Option[ZonedDateTime]    = None,
  birth_place:               Option[String]           = None,
  birth_country:             Option[String]           = None,
  birth_department:          Option[String]           = None,
  phone:                     Option[String]           = None,
  nationality:               Option[String]           = None,
  bio:                       Option[String]           = None,
  sector:                    Option[String]           = None,
  investor_type:             Option[String]           = None,
  linkedin_url:              Option[String]           = None,
  does_pay_taxes:            Option[Boolean]          = None,
  has_been_claimed:          Option[Boolean]          = Some(true),
  city:                      Option[String]           = None,
  wallet_id:                 Option[String]           = None,
  wallet_updated_at:         Option[ZonedDateTime]    = None,
  wallet_type:               Option[String]           = None,
  status:                    Option[String]           = None,
  roles:                     Option[String]           = None,
  targeting_roles:           Option[String]           = None,
  partner_manager_id:        Option[String]           = None,
  investor_score:            Option[Long]             = None,
  tag:                       Option[String]           = None,
  is_pro:                    Option[Boolean]          = None,
  account_validation_status: Option[String]           = None,
  lang:                      Option[String]           = None,
  relatives:                 Option[Seq[Relative]]    = None,
  bankaccounts:              Option[Seq[BankAccount]] = None,
  custom:                    Option[JsObject]         = None
)

object UserData {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  implicit val relative_format = Relative.format
  implicit val bankaccount_format = BankAccount.format
  val format = Jsonx.formatCaseClass[UserData]
}
