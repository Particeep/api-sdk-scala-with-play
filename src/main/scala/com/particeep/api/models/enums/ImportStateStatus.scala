package com.particeep.api.models.enums

object ImportStateStatus {

  sealed abstract class ImportStateStatus extends Product with Enum

  case object PENDING extends ImportStateStatus
  case object RUNNING extends ImportStateStatus
  case object COMPLETED extends ImportStateStatus

  object ImportStateStatus extends EnumHelper[ImportStateStatus] {
    def values: Set[ImportStateStatus] = Set(PENDING, RUNNING, COMPLETED)
  }
}
