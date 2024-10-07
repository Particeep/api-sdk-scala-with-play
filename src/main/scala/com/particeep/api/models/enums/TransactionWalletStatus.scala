package com.particeep.api.models.enums

sealed trait TransactionWalletStatus extends Product with Enum

object TransactionWalletStatus extends EnumHelper[TransactionWalletStatus] {
  case object PENDING   extends TransactionWalletStatus
  case object FAILED    extends TransactionWalletStatus
  case object VALIDATED extends TransactionWalletStatus

  def values: Set[TransactionWalletStatus] = Set(PENDING, FAILED, VALIDATED)
}
