package com.particeep.api.models.enums

sealed trait WalletStatus extends Product with Enum

object WalletStatus extends EnumHelper[WalletStatus] {
  case object LIGHT     extends WalletStatus
  case object CONFIRMED extends WalletStatus

  def values: Set[WalletStatus] = Set(LIGHT, CONFIRMED)
}
