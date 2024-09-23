package com.particeep.api.models.enums

sealed trait Country extends Product with Enum

object Country extends EnumHelper[Country] {
  case object BE extends Country
  case object DE extends Country
  case object FR extends Country
  case object IT extends Country

  def values: Set[Country]                             = Set(BE, DE, FR, IT)
  implicit def stringToCountry(value: String): Country = get(value).getOrElse(FR)
}
