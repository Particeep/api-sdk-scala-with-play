package com.particeep.api.models.enums

sealed abstract class NewSignerType extends Product with Serializable with NewEnum
// to come USUFRUCTUARY_LEGAL, _NATURAL CO_USU

object NewSignerType extends NewEnumSlickHelper[NewSignerType] {
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

  def values: Set[NewSignerType] = Set(
    INVESTOR_NATURAL,
    INVESTOR_LEGAL,
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
