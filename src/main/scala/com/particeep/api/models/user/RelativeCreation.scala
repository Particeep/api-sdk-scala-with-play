package com.particeep.api.models.user

import play.api.libs.json.{ JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.{ Gender, RelativeType }

case class RelativeCreation(
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
  tag:              Option[String]         = None,
  custom:           Option[JsObject]       = None
)

object RelativeCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[RelativeCreation]            = Json.format[RelativeCreation]
}
