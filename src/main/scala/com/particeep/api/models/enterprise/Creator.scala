package com.particeep.api.models.enterprise

import play.api.libs.json.Json

case class Creator(
  creator_type: String,
  creator_name: String
)

object Creator {
  val format = Json.format[Creator]
}
