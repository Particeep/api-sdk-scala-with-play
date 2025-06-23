package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ JsObject, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.{ Gender, RelativeType }

case class Relative(
  id:               String                 = "",
  created_at:       Option[OffsetDateTime] = None,
  created_by:       Option[String]         = None,
  deleted_at:       Option[OffsetDateTime] = None,
  user_id:          String,
  email:            Option[String]         = None,
  first_name:       Option[String]         = None,
  last_name:        Option[String]         = None,
  use_name:         Option[String]         = None,
  gender:           Option[Gender]         = None,
  birthday:         Option[OffsetDateTime] = None,
  birth_place:      Option[String]         = None,
  birth_country:    Option[String]         = None,
  birth_department: Option[String]         = None,
  legal_type:       Option[RelativeType]   = None,
  nationality:      Option[String]         = None,
  birth_cog:        Option[String]         = None,
  tag:              Option[String]         = None,
  custom:           Option[JsObject]       = None
)

object Relative {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[Relative]                    = Jsonx.formatCaseClass[Relative]
}
