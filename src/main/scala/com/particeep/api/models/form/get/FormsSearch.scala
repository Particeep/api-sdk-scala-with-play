package com.particeep.api.models.form.get

import play.api.libs.json.{Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class FormsSearch(
    created_at:      Option[OffsetDateTime] = None,
    name:            Option[String]         = None,
    description:     Option[String]         = None,
    tag:             Option[String]         = None,
    last_updated_at: Option[OffsetDateTime] = None
)

object FormsSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[FormsSearch]
}
