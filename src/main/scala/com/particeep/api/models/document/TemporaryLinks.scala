package com.particeep.api.models.document

import play.api.libs.json.{ Json, OFormat }

final case class TemporaryLinks(links: List[String])

object TemporaryLinks {
  implicit val format: OFormat[TemporaryLinks] = Json.format[TemporaryLinks]
}