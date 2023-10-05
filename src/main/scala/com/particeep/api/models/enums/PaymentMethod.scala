package com.particeep.api.models.enums

object PaymentMethod {

    sealed abstract class PaymentMethod extends Product with Enum
    case object BANK_TRANSFER extends PaymentMethod
    case object DIRECT_DEBIT extends PaymentMethod
    case object BANK_CREDIT extends PaymentMethod
    case object WALLET extends PaymentMethod
    case object CREDIT_CARD extends PaymentMethod
    case object DIRECT_CASHIN extends PaymentMethod
    case object SEPA          extends PaymentMethod

    object PaymentMethod extends EnumHelper[PaymentMethod] {
      def values: Set[PaymentMethod] = Set(BANK_TRANSFER, DIRECT_DEBIT, BANK_CREDIT, WALLET, CREDIT_CARD, DIRECT_CASHIN, SEPA)
    }
}
