package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx

case class UserPatrimonyEdition(
    yearly_income:     Option[Double],
    total_liquidity:   Option[Double],
    yearly_engagement: Option[Double],
    exploit_result:    Option[Double]
)

object UserPatrimonyEdition {
  implicit val format = Jsonx.formatCaseClass[UserPatrimonyEdition]
}