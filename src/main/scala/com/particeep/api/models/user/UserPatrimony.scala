package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx

case class UserPatrimony(
    yearly_income:     Option[Long] = None,
    total_liquidity:   Option[Long] = None,
    yearly_engagement: Option[Long] = None,
    exploit_result:    Option[Long] = None
)

object UserPatrimony {
  val format = Jsonx.formatCaseClass[UserPatrimony]
}
