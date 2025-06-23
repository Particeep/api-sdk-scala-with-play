package com.particeep.api.models.user.relative

import com.particeep.api.models.enums.{ Enum, EnumHelper }

sealed trait RelativeRole extends Product with Enum

object RelativeRole extends EnumHelper[RelativeRole] {

  case object SPOUSE               extends RelativeRole
  case object GUARDIAN             extends RelativeRole
  case object CURATOR              extends RelativeRole
  case object LEGAL_REPRESENTATIVE extends RelativeRole

  def values: Set[RelativeRole] = Set(SPOUSE, GUARDIAN, CURATOR, LEGAL_REPRESENTATIVE)
}
