package com.particeep.api.models.enums

sealed trait NewSignerType extends Product with Serializable with Enum

object NewSignerType extends EnumHelper[NewSignerType] {

  case object INVESTOR_LEGAL       extends NewSignerType
  case object INVESTOR_NATURAL     extends NewSignerType
  case object CO_INVESTOR          extends NewSignerType
  case object PARTNER              extends NewSignerType
  case object USUFRUCTUARY         extends NewSignerType
  case object SPOUSE               extends NewSignerType
  case object GUARDIAN             extends NewSignerType
  case object CURATOR              extends NewSignerType
  case object LEGAL_REPRESENTATIVE extends NewSignerType
  case object EXECUTIVE            extends NewSignerType
  case object BENEFICIARY          extends NewSignerType

  val values: Set[NewSignerType] = Set(
    INVESTOR_LEGAL,
    INVESTOR_NATURAL,
    CO_INVESTOR,
    PARTNER,
    USUFRUCTUARY,
    SPOUSE,
    GUARDIAN,
    CURATOR,
    LEGAL_REPRESENTATIVE,
    EXECUTIVE,
    BENEFICIARY
  )
}
