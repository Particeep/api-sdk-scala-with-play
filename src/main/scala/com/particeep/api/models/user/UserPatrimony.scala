package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx

case class UserPatrimony(
    yearly_income:     Option[Long],
    total_liquidity:   Option[Long],
    yearly_engagement: Option[Long],
    exploit_result:    Option[Long]
)

object UserPatrimony {
  implicit val format = Jsonx.formatCaseClass[UserPatrimony]
}
