package com.particeep.api.models.password_policy

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

import scala.util.matching.Regex

import com.particeep.api.core.Formatter.regex_format

final case class PasswordPolicyUpsert(
  password_minimum_length:       Option[Int]   = None,
  password_regex:                Option[Regex] = None,
  password_expiration_frequency: Option[Long]  = None,
  password_reuse_limit:          Option[Int]   = None,
  password_reuse_period:         Option[Long]  = None,
  login_retry_limit:             Option[Int]   = None
)

object PasswordPolicyUpsert {
  implicit val password_policy_upsert_format: OFormat[PasswordPolicyUpsert] =
    Jsonx.formatCaseClassUseDefaults[PasswordPolicyUpsert]
}
