package com.particeep.api.models.user

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Gender.Gender
import com.particeep.api.models.enums.RelativeType.RelativeType
import play.api.libs.json.{ JsObject, Json }

case class RelativeEdition(
  email:            Option[String]        = None,
  first_name:       Option[String]        = None,
  last_name:        Option[String]        = None,
  gender:           Option[Gender]        = None,
  birthday:         Option[ZonedDateTime] = None,
  birth_place:      Option[String]        = None,
  birth_country:    Option[String]        = None,
  birth_department: Option[String]        = None,
  legal_type:       Option[RelativeType]  = None,
  tag:              Option[String]        = None,
  custom:           Option[JsObject]      = None
)

object RelativeEdition {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[RelativeEdition]
}
