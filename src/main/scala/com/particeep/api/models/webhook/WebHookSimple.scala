package com.particeep.api.models.webhook

import play.api.libs.json._

case class WebHookSimple(
  name: String,
  url:  String
)

object WebHookSimple {
  val format = Json.format[WebHookSimple]
}
