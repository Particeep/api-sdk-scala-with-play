package com.particeep.api.models.enums

sealed trait ControlBlockStatus extends Product with Serializable with Enum

object ControlBlockStatus extends EnumHelper[ControlBlockStatus] {

  case object ONGOING extends ControlBlockStatus
  case object VALIDATED extends ControlBlockStatus
  case object REJECTED extends ControlBlockStatus

  val values: Set[ControlBlockStatus] = Set(ONGOING, VALIDATED, REJECTED)
}
