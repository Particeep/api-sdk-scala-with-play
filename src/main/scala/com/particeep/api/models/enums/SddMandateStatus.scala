package com.particeep.api.models.enums

object SddMandateStatus {

  sealed abstract class SddMandateStatus extends Product with Enum

  case object PENDING extends SddMandateStatus
  case object VALIDATED extends SddMandateStatus
  case object REFUSED extends SddMandateStatus

  object SddMandateStatus extends EnumHelper[SddMandateStatus] {
    def values: Set[SddMandateStatus] = Set(PENDING, VALIDATED, REFUSED)
  }
}
