package com.particeep.api.models.enums

import scala.language.implicitConversions

object Gender {

  sealed abstract class Gender extends Enum
  case object MAN extends Gender { val name: String = "MAN" }
  case object WOMAN extends Gender { val name: String = "WOMAN" }
  case object MISS extends Gender { val name: String = "MISS" }

  object Gender extends EnumHelper[Gender] {
    def values: Set[Gender] = Set(MAN, WOMAN, MISS)

    implicit def string2gender(value: String): Gender = get(value).getOrElse(MAN)
  }
}
