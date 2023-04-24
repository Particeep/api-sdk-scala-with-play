package com.particeep.api.models.form.get_deep

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class FormDeep(
    id:              String                 = "",
    created_at:      Option[OffsetDateTime] = None,
    name:            Option[String]         = None,
    description:     Option[String]         = None,
    tag:             Option[String]         = None,
    last_updated_at: Option[OffsetDateTime] = None,
    sections:        Seq[SectionDeep]       = Seq()
)

object FormDeep {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit val section_format = SectionDeep.format
  val format = Json.format[FormDeep]
}
