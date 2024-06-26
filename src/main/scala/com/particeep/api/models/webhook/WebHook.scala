package com.particeep.api.models.webhook

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class WebHook(
  id:         String                 = "",
  created_at: Option[OffsetDateTime] = None,
  name:       String                 = "",
  url:        String                 = ""
)

object WebHook {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[WebHook]                     = Json.format[WebHook]
}
