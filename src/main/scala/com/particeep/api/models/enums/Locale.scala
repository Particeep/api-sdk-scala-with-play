package com.particeep.api.models.enums

sealed trait Locale extends Product with Enum

object Locale extends EnumHelper[Locale] {
  case object fr_FR extends Locale
  case object en_GB extends Locale
  case object en_US extends Locale
  case object es_ES extends Locale
  case object de_DE extends Locale
  case object pt_PT extends Locale
  case object pt_BR extends Locale
  case object nl_NL extends Locale
  case object nl_BE extends Locale
  case object ar_AR extends Locale

  def values: Set[Locale] = Set(fr_FR, en_GB, en_US, es_ES, de_DE, pt_PT, pt_BR, nl_NL, nl_BE, ar_AR)
}
