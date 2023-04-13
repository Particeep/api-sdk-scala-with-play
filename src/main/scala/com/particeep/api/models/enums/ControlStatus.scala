package com.particeep.api.models.enums

sealed trait ControlStatus extends Product with Serializable with Enum

object ControlStatus extends EnumHelper[ControlStatus] {

  case object ONGOING extends ControlStatus
  case object VALIDATED extends ControlStatus
  case object REJECTED extends ControlStatus
  case object SUCCEED extends ControlStatus

  val values: Set[ControlStatus] = Set(ONGOING, VALIDATED, REJECTED, SUCCEED)
}
