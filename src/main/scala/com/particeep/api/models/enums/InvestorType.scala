package com.particeep.api.models.enums

sealed trait InvestorType extends Product with Enum

object InvestorType extends EnumHelper[InvestorType] {
  case object NATURAL extends InvestorType
  case object LEGAL   extends InvestorType

  def values: Set[InvestorType] = Set(NATURAL, LEGAL)
}
