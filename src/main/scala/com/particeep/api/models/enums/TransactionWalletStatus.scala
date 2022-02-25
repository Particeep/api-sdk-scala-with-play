package com.particeep.api.models.enums

object TransactionWalletStatus {

  sealed abstract class TransactionWalletStatus extends Product with Enum

  case object PENDING extends TransactionWalletStatus
  case object FAILED extends TransactionWalletStatus
  case object VALIDATED extends TransactionWalletStatus

  object TransactionWalletStatus extends EnumHelper[TransactionWalletStatus] {
    def values: Set[TransactionWalletStatus] = Set(PENDING, FAILED, VALIDATED)

    def stringToTransactionWalletStatus(value: String): TransactionWalletStatus = get(value.toUpperCase).getOrElse(PENDING)
  }

}
