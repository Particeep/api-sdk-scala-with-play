package com.particeep.api.models.phonemessaging

import play.api.libs.json.{Json, OFormat}

case class SmsInformation(
  from: String,
  to:   String,
  body: String
)

object SmsInformation {
  val format: OFormat[SmsInformation] = Json.format[SmsInformation]
}
