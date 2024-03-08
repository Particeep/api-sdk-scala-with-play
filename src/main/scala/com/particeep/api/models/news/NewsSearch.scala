package com.particeep.api.models.news

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class NewsSearch(
  target_id:      Option[String]         = None,
  target_type:    Option[String]         = None,
  author_id:      Option[String]         = None,
  title:          Option[String]         = None,
  message:        Option[String]         = None,
  tag:            Option[String]         = None,
  publish_before: Option[OffsetDateTime] = None,
  publish_after:  Option[OffsetDateTime] = None
)

object NewsSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[NewsSearch]
}
