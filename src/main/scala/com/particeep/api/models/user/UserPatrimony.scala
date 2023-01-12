package com.particeep.api.models.user

trait UserPatrimony {
  def yearly_income: Option[Long]
  def total_liquidity: Option[Long]
  def yearly_engagement: Option[Long]
  def exploit_result: Option[Long]
}
