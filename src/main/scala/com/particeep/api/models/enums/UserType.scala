package com.particeep.api.models.enums

object UserType {

  sealed abstract class UserType extends Product with Enum

  case object NATURAL extends UserType
  case object LEGAL extends UserType
  case object SELF_EMPLOYED extends UserType
  case object ASSOCIATION extends UserType

  object UserType extends EnumHelper[UserType] {
    def values: Set[UserType] = Set(NATURAL, LEGAL, ASSOCIATION, SELF_EMPLOYED)
  }

}

