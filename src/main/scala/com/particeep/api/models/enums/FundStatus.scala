package com.particeep.api.models.enums



object FundStatus {

  sealed abstract class FundStatus extends Product with Enum

  case object INIT extends FundStatus
  case object RUNNING extends FundStatus
  case object CLOSING extends FundStatus

  object FundStatus extends EnumHelper[FundStatus] {
    def values: Set[FundStatus] = Set(INIT, RUNNING, CLOSING)

    implicit def stringToFundStatus(value: String): FundStatus = get(value.toUpperCase).getOrElse(INIT)
  }

}
