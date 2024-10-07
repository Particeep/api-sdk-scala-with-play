package com.particeep.api.models.enums

sealed trait SignatureStatus extends Product with Enum

object SignatureStatus extends EnumHelper[SignatureStatus] {
  case object CANCELED           extends SignatureStatus
  case object EXPIRED            extends SignatureStatus
  case object FAILED             extends SignatureStatus
  case object PENDING_VALIDATION extends SignatureStatus
  case object READY              extends SignatureStatus
  case object WAITING            extends SignatureStatus
  case object COMPLETED          extends SignatureStatus

  def values: Set[SignatureStatus] = Set(READY, EXPIRED, COMPLETED, CANCELED, FAILED, PENDING_VALIDATION, WAITING)
}
