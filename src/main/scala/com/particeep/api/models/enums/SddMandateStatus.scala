package com.particeep.api.models.enums

sealed trait SddMandateStatus extends Product with Enum

object SddMandateStatus extends EnumHelper[SddMandateStatus] {
  case object PENDING   extends SddMandateStatus
  case object VALIDATED extends SddMandateStatus
  case object REFUSED   extends SddMandateStatus

  def values: Set[SddMandateStatus] = Set(PENDING, VALIDATED, REFUSED)
}
