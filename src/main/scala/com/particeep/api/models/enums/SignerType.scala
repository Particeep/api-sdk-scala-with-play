package com.particeep.api.models.enums

import com.particeep.api.models.user.relative.RelativeRole

sealed abstract class SignerType extends Product with Serializable with Enum

object SignerType extends EnumHelper[SignerType] {
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

  def values: Set[SignerType] = Set(
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

  def fromInvestorType(investorType: Option[InvestorType]): Option[SignerType] = investorType match {
    case Some(InvestorType.LEGAL)   => Some(SignerType.INVESTOR_LEGAL)
    case Some(InvestorType.NATURAL) => Some(SignerType.INVESTOR_NATURAL)
    case None                       => None
  }

  def fromRelativeRole(relativeRole: Option[RelativeRole]): Option[SignerType] = relativeRole match {
    case Some(RelativeRole.SPOUSE)               => Some(SignerType.SPOUSE)
    case Some(RelativeRole.GUARDIAN)             => Some(SignerType.GUARDIAN)
    case Some(RelativeRole.CURATOR)              => Some(SignerType.CURATOR)
    case Some(RelativeRole.LEGAL_REPRESENTATIVE) => Some(SignerType.LEGAL_REPRESENTATIVE)
    case None                                    => None
  }

  def fromOrganizationRole(organizationRole: Option[OrganizationRole]): Option[SignerType] = organizationRole match {
    case Some(OrganizationRole.EXECUTIVE)            => Some(SignerType.EXECUTIVE)
    case Some(OrganizationRole.BENEFICIARY)          => Some(SignerType.BENEFICIARY)
    case Some(OrganizationRole.LEGAL_REPRESENTATIVE) => Some(SignerType.LEGAL_REPRESENTATIVE)
    case None                                        => None
  }
}
