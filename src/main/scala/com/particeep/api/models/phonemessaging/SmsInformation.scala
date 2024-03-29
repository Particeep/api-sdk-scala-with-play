package com.particeep.api.models.phonemessaging

import play.api.libs.json.Json

case class SmsInformation(
    from: String,
    to:   String,
    body: String
)

object SmsInformation {
  val format = Json.format[SmsInformation]
}
