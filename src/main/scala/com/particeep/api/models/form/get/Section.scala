package com.particeep.api.models.form.get

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, Writes }

case class Section(
    id:          String                      = "",
    created_at:  Option[OffsetDateTime]      = None,
    form_id:     String                      = "",
    name:        Option[Map[String, String]] = None,
    description: Option[Map[String, String]] = None,
    index:       Option[Int]                 = None
)

object Section {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[Section]
}
