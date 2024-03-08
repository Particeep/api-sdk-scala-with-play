package com.particeep.api.models.user

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx

final case class UserEcspData(
    is_sophisticated:            Option[Boolean] = None,
    net_worth:                   Option[Long]    = None,
    investment_advice_threshold: Option[Long]    = None
)
object UserEcspData {
  implicit val format = Jsonx.formatCaseClass[UserEcspData]
}
