package com.particeep.api.models.fund

import play.api.libs.json.Json

case class FundSearch(
  status:       Option[String]  = None,
  name:         Option[String]  = None,
  required_pro: Option[Boolean] = None
)

object FundSearch {
  val format = Json.format[FundSearch]
}
