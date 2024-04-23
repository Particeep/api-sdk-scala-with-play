package com.particeep.api.models.wallet.sepa

import play.api.libs.json.{ Json, OFormat }

case class SepaSddSignature(
  owner_ip:      String = "",
  mobile_number: String = "",
  return_url:    String = "",
  error_url:     String = ""
)

object SepaSddSignature {
  val format: OFormat[SepaSddSignature] = Json.format[SepaSddSignature]
}
