package com.particeep.api.models.phonemessaging

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsObject, Json, Writes }

case class PhoneMessage(
    id:               String                 = "",
    created_at:       Option[OffsetDateTime] = None,
    from_number:      String                 = "",
    to_number:        String                 = "",
    body:             String                 = "",
    status:           String                 = "",
    error_code:       Option[Int]            = None,
    error_message:    Option[String]         = None,
    direction:        String                 = "",
    service_response: String                 = "",
    tag:              Option[String]         = None,
    custom:           Option[JsObject]       = None
)

object PhoneMessage {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[PhoneMessage]
}
