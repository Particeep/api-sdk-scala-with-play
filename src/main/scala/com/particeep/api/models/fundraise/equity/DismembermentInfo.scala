package com.particeep.api.models.fundraise.equity

import play.api.libs.json.Json

case class DismembermentInfo(dismemberment_rate: Double)

object DismembermentInfo {
  val format = Json.format[DismembermentInfo]
}
