package com.particeep.api.models.enums

object WalletStatus {

  sealed abstract class WalletStatus extends Product with Enum

  case object LIGHT extends WalletStatus
  case object CONFIRMED extends WalletStatus

  object WalletStatus extends EnumHelper[WalletStatus] {
    def values: Set[WalletStatus] = Set(LIGHT, CONFIRMED)
  }
}
