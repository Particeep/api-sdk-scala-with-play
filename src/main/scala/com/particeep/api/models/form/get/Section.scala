package com.particeep.api.models.form.get

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class Section(
  id:          String                      = "",
  created_at:  Option[ZonedDateTime]       = None,
  form_id:     String                      = "",
  name:        Option[Map[String, String]] = None,
  description: Option[Map[String, String]] = None,
  index:       Option[Int]                 = None
)

object Section {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[Section]
}
