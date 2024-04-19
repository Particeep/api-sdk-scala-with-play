package com.particeep.api.models.webhook

import play.api.libs.json.{Json, OFormat}

case class WebHookSimple(
  name: String,
  url:  String
)

object WebHookSimple {
  val format: OFormat[WebHookSimple] = Json.format[WebHookSimple]
}
