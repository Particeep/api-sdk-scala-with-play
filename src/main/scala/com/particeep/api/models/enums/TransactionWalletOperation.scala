package com.particeep.api.models.enums

object TransactionWalletOperation {

  sealed abstract class TransactionWalletOperation extends Product with Enum
  case object WALLET_PAYMENT extends TransactionWalletOperation
  case object CASHIN_CB extends TransactionWalletOperation
  case object CASHIN_REGISTERED_CB extends TransactionWalletOperation
  case object CASHIN_SDD extends TransactionWalletOperation
  case object CASHIN_TRANSFER extends TransactionWalletOperation
  case object CASHIN_CHECK extends TransactionWalletOperation
  case object CASHOUT extends TransactionWalletOperation
  case object TRANSFER extends TransactionWalletOperation
  case object REFUND_WALLET_PAYMENT extends TransactionWalletOperation
  case object SCHEDULED_WALLET_PAYMENT extends TransactionWalletOperation
  case object SCHEDULED_TAX_WALLET_PAYMENT extends TransactionWalletOperation
  case object REFUND_MISTAKE extends TransactionWalletOperation

  object TransactionWalletOperation extends EnumHelper[TransactionWalletOperation] {
    def values: Set[TransactionWalletOperation] = Set(WALLET_PAYMENT, CASHIN_CB, CASHIN_REGISTERED_CB, CASHIN_SDD, CASHIN_TRANSFER,
      CASHIN_CHECK, CASHOUT, TRANSFER, REFUND_WALLET_PAYMENT, SCHEDULED_WALLET_PAYMENT, SCHEDULED_TAX_WALLET_PAYMENT, REFUND_MISTAKE)
  }
}
