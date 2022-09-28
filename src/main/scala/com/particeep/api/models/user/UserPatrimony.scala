package com.particeep.api.models.user

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx

case class UserPatrimony(
    monthly_permanent:   Option[Long],
    temporary:           Option[Long],
    monthly_financial:   Option[Long],
    financial_patrimony: Option[Long],
    monthly_engagement:  Option[Long],
    exploit_result:      Option[Long]
)

object UserPatrimony {
  implicit val format = Jsonx.formatCaseClass[UserPatrimony]
}
