package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx

case class UserPatrimonyEdition(
    yearly_income:     Double,
    total_liquidity:   Double,
    yearly_engagement: Double,
    exploit_result:    Double
)

object UserPatrimonyEdition {
  implicit val format = Jsonx.formatCaseClass[UserPatrimonyEdition]
}