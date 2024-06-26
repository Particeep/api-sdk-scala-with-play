package com.particeep.api.models.enums

object TransactionStatus {

  sealed abstract class TransactionStatus extends Product with Enum
  case object PENDING                     extends TransactionStatus
  case object FROZEN                      extends TransactionStatus
  case object SUCCEEDED                   extends TransactionStatus
  case object REFUNDED                    extends TransactionStatus
  case object CANCELLED                   extends TransactionStatus

  object TransactionStatus extends EnumHelper[TransactionStatus] {
    def values: Set[TransactionStatus] = Set(PENDING, FROZEN, SUCCEEDED, REFUNDED, CANCELLED)

    implicit def stringToTransactionStatus(value: String): TransactionStatus = get(value).getOrElse(PENDING)
  }
}
