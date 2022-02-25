package com.particeep.api.models.enums

object CalculatorType {

  sealed abstract class CalculatorType extends Product with Enum
  case object Constant extends CalculatorType
  case object CouponConstant extends CalculatorType
  case object InFine extends CalculatorType

  object CalculatorType extends EnumHelper[CalculatorType] {
    def values: Set[CalculatorType] = Set(Constant, CouponConstant, InFine)
  }
}
