package com.particeep.api.models.enums

sealed trait BankAccountStatus extends Product with Enum

object BankAccountStatus extends EnumHelper[BankAccountStatus] {
  case object PENDING     extends BankAccountStatus
  case object VALIDATED   extends BankAccountStatus
  case object REFUSED     extends BankAccountStatus
  case object DEACTIVATED extends BankAccountStatus

  def values: Set[BankAccountStatus] = Set(PENDING, VALIDATED, REFUSED, DEACTIVATED)
}
