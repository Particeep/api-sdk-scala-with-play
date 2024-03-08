package com.particeep.api.models.news

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.PaginatedSequence

case class NewsPrevAndNext(
  prev: PaginatedSequence[News],
  next: PaginatedSequence[News]
)

object NewsPrevAndNext {
  implicit val news_format: OFormat[News] = News.format
  val format                              = Json.format[NewsPrevAndNext]
}
