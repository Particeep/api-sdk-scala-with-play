package com.particeep.api.models.form.get

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class FormsSearch(
    created_at:      Option[OffsetDateTime] = None,
    name:            Option[String]         = None,
    description:     Option[String]         = None,
    tag:             Option[String]         = None,
    last_updated_at: Option[OffsetDateTime] = None
)

object FormsSearch {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[FormsSearch]
}
