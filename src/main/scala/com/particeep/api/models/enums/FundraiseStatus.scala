package com.particeep.api.models.enums

object FundraiseStatus {

  sealed abstract class FundraiseStatus extends Product with Enum
  case object INIT                      extends FundraiseStatus
  case object UNDER_REVIEW              extends FundraiseStatus
  case object RUNNING                   extends FundraiseStatus
  case object SUCCEEDED                 extends FundraiseStatus
  case object REFUND_ONGOING            extends FundraiseStatus
  case object REFUNDED                  extends FundraiseStatus

  object FundraiseStatus extends EnumHelper[FundraiseStatus] {
    def values: Set[FundraiseStatus] = Set(INIT, UNDER_REVIEW, RUNNING, SUCCEEDED, REFUND_ONGOING, REFUNDED)

    implicit def stringToFundraiseStatus(value: String): FundraiseStatus = get(value).getOrElse(INIT)
  }
}
