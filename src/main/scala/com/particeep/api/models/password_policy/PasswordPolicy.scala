package com.particeep.api.models.password_policy

import play.api.libs.json.{ Format, Json }

import java.time.OffsetDateTime

final case class PasswordPolicy(
  id:                            String,
  created_at:                    OffsetDateTime,
  created_by:                    String,
  updated_at:                    OffsetDateTime,
  password_minimum_length:       Long,
  password_regex:                String,
  password_expiration_frequency: Long, // in seconds
  password_reuse_limit:          Long,
  password_reuse_period:         Long, // in seconds
  login_retry_limit:             Long
)

object PasswordPolicy {
  implicit val pp_format: Format[PasswordPolicy] = Json.format[PasswordPolicy]
}
