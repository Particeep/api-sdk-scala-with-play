package com.particeep.api.models.enums

sealed trait FundraiseStatus extends Product with Enum

object FundraiseStatus extends EnumHelper[FundraiseStatus] {
  case object INIT           extends FundraiseStatus
  case object UNDER_REVIEW   extends FundraiseStatus
  case object RUNNING        extends FundraiseStatus
  case object SUCCEEDED      extends FundraiseStatus
  case object REFUND_ONGOING extends FundraiseStatus
  case object REFUNDED       extends FundraiseStatus

  def values: Set[FundraiseStatus]                                     = Set(INIT, UNDER_REVIEW, RUNNING, SUCCEEDED, REFUND_ONGOING, REFUNDED)
  implicit def stringToFundraiseStatus(value: String): FundraiseStatus = get(value).getOrElse(INIT)
}
