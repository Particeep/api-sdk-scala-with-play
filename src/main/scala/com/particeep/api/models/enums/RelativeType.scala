package com.particeep.api.models.enums

sealed trait RelativeType extends Product with Enum

object RelativeType extends EnumHelper[RelativeType] {
  case object CONJOINT     extends RelativeType
  case object USUFRUCTUARY extends RelativeType
  case object PAYER        extends RelativeType

  def values: Set[RelativeType] = Set(CONJOINT, USUFRUCTUARY, PAYER)
}
