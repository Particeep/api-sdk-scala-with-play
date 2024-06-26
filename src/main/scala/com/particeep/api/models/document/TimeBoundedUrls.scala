package com.particeep.api.models.document

import play.api.libs.json.{ Json, OFormat }

final case class TimeBoundedUrls(urls: List[String])

object TimeBoundedUrls {
  implicit val format: OFormat[TimeBoundedUrls] = Json.format[TimeBoundedUrls]
}
