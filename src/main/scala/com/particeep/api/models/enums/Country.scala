package com.particeep.api.models.enums

import scala.language.implicitConversions

object Country {
  sealed abstract class Country extends Enum

  case object BE extends Country { val name: String = "BE" }
  case object DE extends Country { val name: String = "DE" }
  case object FR extends Country { val name: String = "FR" }
  case object IT extends Country { val name: String = "IT" }

  object Country extends EnumHelper[Country] {
    def values: Set[Country] = Set(BE, DE, FR, IT)

    implicit def stringToCountry(value: String): Country = get(value).getOrElse(FR)
  }
}
