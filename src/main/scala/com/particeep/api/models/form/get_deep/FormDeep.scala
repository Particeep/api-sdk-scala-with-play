package com.particeep.api.models.form.get_deep

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class FormDeep(
  id:              String                 = "",
  created_at:      Option[OffsetDateTime] = None,
  name:            Option[String]         = None,
  description:     Option[String]         = None,
  tag:             Option[List[String]]   = None,
  last_updated_at: Option[OffsetDateTime] = None,
  sections:        Seq[SectionDeep]       = Seq()
)

object FormDeep {
  implicit val date_format: Writes[OffsetDateTime]  = Formatter.OffsetDateTimeWrites
  implicit val section_format: OFormat[SectionDeep] = SectionDeep.format
  val format: OFormat[FormDeep]                     = Json.format[FormDeep]
}
