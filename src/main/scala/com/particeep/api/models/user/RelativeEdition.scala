package com.particeep.api.models.user

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Gender.Gender
import com.particeep.api.models.enums.RelativeType.RelativeType

case class RelativeEdition(
  email:            Option[String]         = None,
  first_name:       Option[String]         = None,
  last_name:        Option[String]         = None,
  gender:           Option[Gender]         = None,
  birthday:         Option[OffsetDateTime] = None,
  birth_place:      Option[String]         = None,
  birth_country:    Option[String]         = None,
  birth_department: Option[String]         = None,
  legal_type:       Option[RelativeType]   = None,
  tag:              Option[String]         = None,
  custom:           Option[JsObject]       = None
)

object RelativeEdition {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[RelativeEdition]
}
