package com.particeep.api.models.fundraise.equity

import play.api.libs.json.{Json, OFormat}

case class DismembermentInfo(dismemberment_rate: Double)

object DismembermentInfo {
  val format: OFormat[DismembermentInfo] = Json.format[DismembermentInfo]
}
