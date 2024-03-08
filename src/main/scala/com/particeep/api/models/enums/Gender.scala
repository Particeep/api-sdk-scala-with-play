package com.particeep.api.models.enums



object Gender {

  sealed abstract class Gender extends Product with Enum
  case object MAN extends Gender
  case object WOMAN extends Gender

  object Gender extends EnumHelper[Gender] {
    def values: Set[Gender] = Set(MAN, WOMAN)

    implicit def string2gender(value: String): Gender = get(value).getOrElse(MAN)
  }
}
