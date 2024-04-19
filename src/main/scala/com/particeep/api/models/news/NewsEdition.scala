package com.particeep.api.models.news

import play.api.libs.json.{JsObject, Json, OFormat}

case class NewsEdition(
  title:       Option[String]   = None,
  message:     Option[String]   = None,
  img_url:     Option[String]   = None,
  content_url: Option[String]   = None,
  tag:         Option[String]   = None,
  custom:      Option[JsObject] = None
)

object NewsEdition {
  val format: OFormat[NewsEdition] = Json.format[NewsEdition]
}
