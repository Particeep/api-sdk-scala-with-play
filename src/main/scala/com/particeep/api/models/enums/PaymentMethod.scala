package com.particeep.api.models.enums

object PaymentMethod {

  sealed abstract class PaymentMethod extends Product with Enum
  case object WALLET extends PaymentMethod
  case object CREDIT_CARD extends PaymentMethod
  case object DIRECT_CASHIN extends PaymentMethod

  object PaymentMethod extends EnumHelper[PaymentMethod] {
    def values: Set[PaymentMethod] = Set(WALLET, CREDIT_CARD, DIRECT_CASHIN)
  }
}
