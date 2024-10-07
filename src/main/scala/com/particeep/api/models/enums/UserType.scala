package com.particeep.api.models.enums

sealed trait UserType extends Product with Enum

object UserType extends EnumHelper[UserType] {
  case object NATURAL       extends UserType
  case object LEGAL         extends UserType
  case object SELF_EMPLOYED extends UserType
  case object ASSOCIATION   extends UserType

  def values: Set[UserType] = Set(NATURAL, LEGAL, ASSOCIATION, SELF_EMPLOYED)
}
