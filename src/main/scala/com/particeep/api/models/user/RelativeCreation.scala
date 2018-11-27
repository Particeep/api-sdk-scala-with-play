package com.particeep.api.models.user

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Gender.Gender
import com.particeep.api.models.enums.RelativeType.RelativeType
import org.joda.time.DateTime
import play.api.libs.json.{JsObject, Json}

case class RelativeCreation(
  email:         Option[String]       = None,
  first_name:    Option[String]       = None,
  last_name:     Option[String]       = None,
  gender:        Option[Gender]       = None,
  birthday:      Option[DateTime]     = None,
  birth_place:   Option[String]       = None,
  birth_country: Option[String]       = None,
  legal_type:    Option[RelativeType] = None,
  tag:           Option[String]       = None,
  custom:        Option[JsObject]     = None
)

object RelativeCreation {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[RelativeCreation]
}