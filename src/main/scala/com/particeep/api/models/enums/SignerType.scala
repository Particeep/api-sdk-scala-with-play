package com.particeep.api.models.enums

object SignerType {
  sealed abstract class SignerType extends Product with Serializable with Enum {
    override val name = this.productPrefix
  }
  // to come USUFRUCTUARY_LEGAL, _NATURAL CO_USU
  case object INVESTOR_LEGAL       extends SignerType
  case object INVESTOR_NATURAL     extends SignerType
  case object CO_INVESTOR          extends SignerType
  case object PARTNER              extends SignerType
  case object USUFRUCTUARY         extends SignerType
  case object SPOUSE               extends SignerType
  case object GUARDIAN             extends SignerType
  case object CURATOR              extends SignerType
  case object LEGAL_REPRESENTATIVE extends SignerType
  case object EXECUTIVE            extends SignerType
  case object BENEFICIARY          extends SignerType

  object SignerType extends EnumHelper[SignerType] {
    def values: Set[SignerType] = Set(
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
}
