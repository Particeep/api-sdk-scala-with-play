package com.particeep.api.models.news

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class NewsSearch(
    target_id:      Option[String]         = None,
    target_type:    Option[String]         = None,
    author_id:      Option[String]         = None,
    title:          Option[String]         = None,
    message:        Option[String]         = None,
    is_report:      Option[Boolean]        = None,
    tag:            Option[String]         = None,
    publish_before: Option[OffsetDateTime] = None,
    publish_after:  Option[OffsetDateTime] = None
)

object NewsSearch {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[NewsSearch]
}
