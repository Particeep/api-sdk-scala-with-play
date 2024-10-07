package com.particeep.api.models.kyc

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.UserType
import com.particeep.api.models.enums.UserType.NATURAL

case class KycCreation(
  owner_id:   String         = "",
  owner_type: String         = "",
  user_type:  UserType       = NATURAL,
  owner_ip:   Option[String] = None
)

object KycCreation {
  val format: OFormat[KycCreation] = Json.format[KycCreation]
}
