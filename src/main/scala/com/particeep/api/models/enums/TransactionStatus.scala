package com.particeep.api.models.enums

sealed trait TransactionStatus extends Product with Enum

object TransactionStatus extends EnumHelper[TransactionStatus] {
  case object PENDING   extends TransactionStatus
  case object FROZEN    extends TransactionStatus
  case object SUCCEEDED extends TransactionStatus
  case object REFUNDED  extends TransactionStatus
  case object CANCELLED extends TransactionStatus

  def values: Set[TransactionStatus] = Set(PENDING, FROZEN, SUCCEEDED, REFUNDED, CANCELLED)

  implicit def stringToTransactionStatus(value: String): TransactionStatus = get(value).getOrElse(PENDING)
}
