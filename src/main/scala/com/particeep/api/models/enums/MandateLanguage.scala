package com.particeep.api.models.enums

object MandateLanguage {

  sealed abstract class MandateLanguage extends Product with Enum

  case object fr extends MandateLanguage
  case object en extends MandateLanguage
  case object de extends MandateLanguage
  case object es extends MandateLanguage

  object MandateLanguage extends EnumHelper[MandateLanguage] {
    def values: Set[MandateLanguage] = Set(fr, en, de, es)

    val defaultLocal: fr.type = fr
  }
}
