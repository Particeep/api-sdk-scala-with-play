package com.particeep.api.models.enums

sealed trait MandateLanguage extends Product with Enum

object MandateLanguage extends EnumHelper[MandateLanguage] {
  case object fr extends MandateLanguage
  case object en extends MandateLanguage
  case object de extends MandateLanguage
  case object es extends MandateLanguage

  def values: Set[MandateLanguage] = Set(fr, en, de, es)

  val defaultLocal: fr.type = fr
}
