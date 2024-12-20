package com.particeep.api.models.form.get

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class Form(
  id:              String                 = "",
  created_at:      Option[OffsetDateTime] = None,
  name:            Option[String]         = None,
  description:     Option[String]         = None,
  tag:             Option[List[String]]   = None,
  last_updated_at: Option[OffsetDateTime] = None
)

object Form {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[Form]                        = Json.format[Form]
}
