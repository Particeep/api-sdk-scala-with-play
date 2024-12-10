package com.particeep.api.models.enums

sealed trait OrganizationRole extends Product with Enum

object OrganizationRole extends EnumHelper[OrganizationRole] {
  case object EXECUTIVE            extends OrganizationRole
  case object BENEFICIARY          extends OrganizationRole
  case object LEGAL_REPRESENTATIVE extends OrganizationRole

  def values: Set[OrganizationRole] = Set(EXECUTIVE, BENEFICIARY, LEGAL_REPRESENTATIVE)
}
