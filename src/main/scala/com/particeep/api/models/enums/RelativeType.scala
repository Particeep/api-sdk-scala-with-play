package com.particeep.api.models.enums

object RelativeType {
  sealed abstract class RelativeType extends Product with Enum
  case object CONJOINT               extends RelativeType
  case object USUFRUCTUARY           extends RelativeType
  case object PAYER                  extends RelativeType

  object RelativeType extends EnumHelper[RelativeType] {
    def values: Set[RelativeType] = Set(CONJOINT, USUFRUCTUARY, PAYER)
  }
}
