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
) {
  private[this] def compute_net_patrimony: Long = {
    val monthly_permanent_value = monthly_permanent.getOrElse(0L) * 12
    val temporary_value = temporary.getOrElse(0L)
    val monthly_financial_value = monthly_financial.getOrElse(0L) * 12
    val financial_patrimony_value = financial_patrimony.getOrElse(0L)
    val monthly_engagement_value = monthly_engagement.getOrElse(0L) * 12

    monthly_permanent_value + temporary_value + monthly_financial_value + financial_patrimony_value - monthly_engagement_value
  }

  def net_patrimony: Long = {
    exploit_result.getOrElse(compute_net_patrimony)
  }
}

object UserPatrimony {
  implicit val format = Jsonx.formatCaseClass[UserPatrimony]
}
