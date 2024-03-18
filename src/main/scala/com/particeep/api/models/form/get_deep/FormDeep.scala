package com.particeep.api.models.form.get_deep

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, OFormat, Writes }

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
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val section_format: OFormat[SectionDeep] = SectionDeep.format
  val format = Json.format[FormDeep]
}
