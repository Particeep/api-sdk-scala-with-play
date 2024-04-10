package com.particeep.api.models.enums

object BankAccountStatus {

  sealed abstract class BankAccountStatus extends Product with Enum

  case object PENDING     extends BankAccountStatus
  case object VALIDATED   extends BankAccountStatus
  case object REFUSED     extends BankAccountStatus
  case object DEACTIVATED extends BankAccountStatus

  object BankAccountStatus extends EnumHelper[BankAccountStatus] {
    def values: Set[BankAccountStatus] = Set(PENDING, VALIDATED, REFUSED, DEACTIVATED)
  }
}
