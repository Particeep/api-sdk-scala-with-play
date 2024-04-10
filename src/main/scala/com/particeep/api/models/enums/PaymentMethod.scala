package com.particeep.api.models.enums

sealed trait PaymentMethod extends Product with Serializable with Enum

object PaymentMethod extends EnumHelper[PaymentMethod] {

  sealed trait OnlinePaymentMethod  extends PaymentMethod
  sealed trait OfflinePaymentMethod extends PaymentMethod

  object OnlinePaymentMethod extends EnumHelper[OnlinePaymentMethod] {
    case object WALLET        extends PaymentMethod with OnlinePaymentMethod
    case object CREDIT_CARD   extends PaymentMethod with OnlinePaymentMethod
    case object DIRECT_CASHIN extends PaymentMethod with OnlinePaymentMethod
    case object SEPA          extends PaymentMethod with OnlinePaymentMethod

    override def values: Set[OnlinePaymentMethod] = Set(WALLET, CREDIT_CARD, DIRECT_CASHIN, SEPA)
  }

  object OfflinePaymentMethod extends EnumHelper[OfflinePaymentMethod] {
    case object BANK_TRANSFER extends PaymentMethod with OfflinePaymentMethod
    case object DIRECT_DEBIT  extends PaymentMethod with OfflinePaymentMethod
    case object BANK_CREDIT   extends PaymentMethod with OfflinePaymentMethod

    override def values: Set[OfflinePaymentMethod] = Set(BANK_TRANSFER, DIRECT_DEBIT, BANK_CREDIT)
  }

  def values: Set[PaymentMethod] = OnlinePaymentMethod.values ++ OfflinePaymentMethod.values

}
