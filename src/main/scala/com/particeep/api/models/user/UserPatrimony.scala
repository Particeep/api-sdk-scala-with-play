package com.particeep.api.models.user

trait UserPatrimony {
  val yearly_income: Option[Long]
  val total_liquidity: Option[Long]
  val yearly_engagement: Option[Long]
  val exploit_result: Option[Long]
}
