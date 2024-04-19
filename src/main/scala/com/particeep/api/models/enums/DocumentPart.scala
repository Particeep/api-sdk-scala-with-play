package com.particeep.api.models.enums

sealed trait DocumentPart extends Product with Enum

object DocumentPart extends EnumHelper[DocumentPart] {

  case object RECTO extends DocumentPart
  case object VERSO extends DocumentPart
  case object OTHER extends DocumentPart

  val values: Set[DocumentPart] = Set(RECTO, VERSO, OTHER)
}
