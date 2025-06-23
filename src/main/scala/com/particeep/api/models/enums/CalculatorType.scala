package com.particeep.api.models.enums

sealed trait CalculatorType extends Product with Enum

object CalculatorType extends EnumHelper[CalculatorType] {
  case object Constant       extends CalculatorType
  case object CouponConstant extends CalculatorType
  case object InFine         extends CalculatorType

  def values: Set[CalculatorType] = Set(Constant, CouponConstant, InFine)
}
