package com.particeep.api.models.enums

import com.particeep.api.models.user.relative.RelativeRole

sealed trait NewSignerType extends Product with Serializable with Enum
// to come USUFRUCTUARY_LEGAL, _NATURAL, CO_USU

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

  def toOld(newSignerType: NewSignerType): SignerType = newSignerType match {
    case INVESTOR_LEGAL | INVESTOR_NATURAL => SignerType.INVESTOR
    case CO_INVESTOR                       => SignerType.CONJOINT
    case PARTNER                           => SignerType.PARTNER
    case USUFRUCTUARY                      => SignerType.USUFRUCTARY
    case SPOUSE                            => SignerType.SPOUSE
    case GUARDIAN                          => SignerType.GUARDIAN
    case CURATOR                           => SignerType.CURATOR
    case LEGAL_REPRESENTATIVE              => SignerType.LEGAL_REPRESENTATIVE
    case EXECUTIVE                         => SignerType.EXECUTIVE
    case BENEFICIARY                       => SignerType.BENEFICIARY
  }

  def fromInvestorType(investorType: InvestorType): NewSignerType = investorType match {
    case InvestorType.LEGAL   => INVESTOR_LEGAL
    case InvestorType.NATURAL => INVESTOR_NATURAL
  }

  def fromRelativeRole(relativeRole: RelativeRole): NewSignerType = relativeRole match {
    case RelativeRole.SPOUSE               => SPOUSE
    case RelativeRole.GUARDIAN             => GUARDIAN
    case RelativeRole.CURATOR              => CURATOR
    case RelativeRole.LEGAL_REPRESENTATIVE => LEGAL_REPRESENTATIVE
  }

  def fromOrganizationRole(organizationRole: OrganizationRole): NewSignerType = organizationRole match {
    case OrganizationRole.EXECUTIVE            => EXECUTIVE
    case OrganizationRole.BENEFICIARY          => BENEFICIARY
    case OrganizationRole.LEGAL_REPRESENTATIVE => LEGAL_REPRESENTATIVE
  }
}
