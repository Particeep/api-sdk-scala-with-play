package com.particeep.api.models.document

import play.api.libs.json.{ Json, OFormat }

final case class TimeBoundedLinks(links: List[String])

object TimeBoundedLinks {
  implicit val format: OFormat[TimeBoundedLinks] = Json.format[TimeBoundedLinks]
}