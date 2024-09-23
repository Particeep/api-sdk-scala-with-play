package com.particeep.api.models.enums

sealed trait WalletType extends Product with Enum

object WalletType extends EnumHelper[WalletType] {
  case object NATURAL extends WalletType
  case object LEGAL   extends WalletType

  def values: Set[WalletType] = Set(NATURAL, LEGAL)

  implicit def string2walletType(value: String): WalletType = get(value).getOrElse(NATURAL)
}
