package com.particeep.api.models.enums

object InvestorType {

  sealed abstract class InvestorType extends Product with Enum

  case object NATURAL extends InvestorType
  case object LEGAL   extends InvestorType

  object InvestorType extends EnumHelper[InvestorType] {
    def values: Set[InvestorType] = Set(NATURAL, LEGAL)
  }
}
