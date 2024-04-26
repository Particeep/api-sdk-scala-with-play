package com.particeep.api.models.password_policy

import play.api.libs.json.{ Format, Json, Writes }

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

  private[this] val write_pp: Writes[PasswordPolicy] = (pp: PasswordPolicy) => {
    Json.obj(
      "id"                            -> pp.id,
      "created_at"                    -> pp.created_at,
      "created_by"                    -> pp.created_by,
      "updated_at"                    -> pp.updated_at,
      "password_minimum_length"       -> pp.password_minimum_length,
      "password_regex"                -> pp.password_regex.toString(),
      "password_expiration_frequency" -> pp.password_expiration_frequency.toDays,
      "password_reuse_limit"          -> pp.password_reuse_limit,
      "password_reuse_period"         -> pp.password_reuse_period.toSeconds,
      "login_retry_limit"             -> pp.login_retry_limit
    )
  }

  implicit val pp_format: Format[PasswordPolicy] = Format(Json.reads[PasswordPolicy], write_pp)
}
