package com.particeep.api.models.kyc

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.KycStatus.{ CREATED, KycStatus }

case class KycGroup(
  owner_id:   String    = "",
  owner_type: String    = "",
  status:     KycStatus = CREATED,
  docs:       Seq[Kyc]  = Seq()
)

object KycGroup {
  implicit val kyc_format: OFormat[Kyc] = Kyc.format
  val format: OFormat[KycGroup]         = Json.format[KycGroup]
}
