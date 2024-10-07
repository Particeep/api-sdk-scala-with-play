package com.particeep.api.models.enums

sealed trait ImportStateStatus extends Product with Enum

object ImportStateStatus extends EnumHelper[ImportStateStatus] {
  case object PENDING   extends ImportStateStatus
  case object RUNNING   extends ImportStateStatus
  case object COMPLETED extends ImportStateStatus

  def values: Set[ImportStateStatus] = Set(PENDING, RUNNING, COMPLETED)
}
