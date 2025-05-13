package com.particeep.api.utils

import com.particeep.api.models.enums.{ InvestorType, OrganizationRole, SignerType }
import com.particeep.api.models.user.relative.RelativeRole

object SignerTypeUtils {
  def fromInvestorType(investorType: InvestorType): SignerType             = investorType match {
    case InvestorType.LEGAL   => SignerType.INVESTOR_LEGAL
    case InvestorType.NATURAL => SignerType.INVESTOR_NATURAL
  }
  def fromRelativeRole(relativeRole: RelativeRole): SignerType             = relativeRole match {
    case RelativeRole.SPOUSE               => SignerType.SPOUSE
    case RelativeRole.GUARDIAN             => SignerType.GUARDIAN
    case RelativeRole.CURATOR              => SignerType.CURATOR
    case RelativeRole.LEGAL_REPRESENTATIVE => SignerType.LEGAL_REPRESENTATIVE
  }
  def fromOrganizationRole(organizationRole: OrganizationRole): SignerType = organizationRole match {
    case OrganizationRole.EXECUTIVE            => SignerType.EXECUTIVE
    case OrganizationRole.BENEFICIARY          => SignerType.BENEFICIARY
    case OrganizationRole.LEGAL_REPRESENTATIVE => SignerType.LEGAL_REPRESENTATIVE
  }
}
