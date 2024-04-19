package com.particeep.api.models.news

import play.api.libs.json.{JsObject, Json, OFormat, Writes}

import java.time.{OffsetDateTime, ZoneOffset}
import com.particeep.api.core.Formatter

case class News(
  id:          String                 = "",
  deleted_at:  Option[OffsetDateTime] = None,
  target_id:   String                 = "",
  target_type: String                 = "",
  author_id:   String                 = "",
  publish_at:  OffsetDateTime         = OffsetDateTime.now(ZoneOffset.UTC),
  title:       Option[String]         = None,
  message:     Option[String]         = None,
  img_url:     Option[String]         = None,
  content_url: Option[String]         = None,
  tag:         Option[String]         = None,
  custom:      Option[JsObject]       = None
)

object News {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[News] = Json.format[News]
}
