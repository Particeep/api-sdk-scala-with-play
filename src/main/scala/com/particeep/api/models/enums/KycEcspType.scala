package com.particeep.api.models.enums

sealed trait KycEcspType extends Product with Serializable with Enum

object KycEcspType extends EnumHelper[KycEcspType] {
  case object LEGAL   extends KycEcspType
  case object NATURAL extends KycEcspType

  def values: Set[KycEcspType] = Set(LEGAL, NATURAL)
}
