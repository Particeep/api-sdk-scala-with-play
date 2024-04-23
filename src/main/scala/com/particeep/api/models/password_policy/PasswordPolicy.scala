package com.particeep.api.models.password_policy

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

import java.time.{ Duration, OffsetDateTime }
import scala.util.matching.Regex

import com.particeep.api.core.Formatter.regex_format

final case class PasswordPolicy(
  id:                            String,
  created_at:                    OffsetDateTime,
  created_by:                    String,
  updated_at:                    OffsetDateTime,
  password_minimum_length:       Int,
  password_regex:                Regex,
  password_expiration_frequency: Duration,
  password_reuse_limit:          Int,
  password_reuse_period:         Duration,
  login_retry_limit:             Int
)

object PasswordPolicy {
  implicit val password_policy_format: OFormat[PasswordPolicy] = Jsonx.formatCaseClassUseDefaults[PasswordPolicy]
}
