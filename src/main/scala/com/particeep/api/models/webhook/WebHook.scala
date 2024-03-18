package com.particeep.api.models.webhook

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, Writes }

case class WebHook(
    id:         String                 = "",
    created_at: Option[OffsetDateTime] = None,
    name:       String                 = "",
    url:        String                 = ""
)

object WebHook {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[WebHook]
}
