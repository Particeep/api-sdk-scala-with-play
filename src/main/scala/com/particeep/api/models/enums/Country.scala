package com.particeep.api.models.enums

import scala.language.implicitConversions

object Country {
  sealed abstract class Country extends Product with Enum

  case object BE extends Country
  case object DE extends Country
  case object FR extends Country
  case object IT extends Country

  object Country extends EnumHelper[Country] {
    def values: Set[Country] = Set(BE, DE, FR, IT)

    implicit def stringToCountry(value: String): Country = get(value).getOrElse(FR)
  }
}
