package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx

case class UserPatrimony(
    yearly_income:     Long = 0L,
    total_liquidity:   Long = 0L,
    yearly_engagement: Long = 0L,
    exploit_result:    Long = 0L
)

object UserPatrimony {
  implicit val format = Jsonx.formatCaseClass[UserPatrimony]
}
