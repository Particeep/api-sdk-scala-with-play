package com.particeep.api.models.enums

sealed trait SignerType extends Product with Serializable with Enum

object SignerType extends EnumHelper[SignerType] {

  case object CONJOINT     extends SignerType
  case object INVESTOR     extends SignerType
  case object PARTNER      extends SignerType
  case object USUFRUCTARY  extends SignerType
  case object ENTREPRENEUR extends SignerType

  val values: Set[SignerType] = Set(CONJOINT, INVESTOR, PARTNER, USUFRUCTARY, ENTREPRENEUR)
}
