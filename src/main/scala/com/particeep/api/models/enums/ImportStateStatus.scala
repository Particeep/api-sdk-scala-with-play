package com.particeep.api.models.enums

object ImportStateStatus {

  sealed abstract class ImportStateStatus extends Enum

  case object PENDING extends ImportStateStatus { val name: String = "PENDING" }
  case object RUNNING extends ImportStateStatus { val name: String = "RUNNING" }
  case object COMPLETED extends ImportStateStatus { val name: String = "COMPLETED" }

  object ImportStateStatus extends EnumHelper[ImportStateStatus] {
    def values: Set[ImportStateStatus] = Set(PENDING, RUNNING, COMPLETED)
  }
}
