package com.particeep.api.models.enums

sealed trait DocumentPart extends Product with Enum

object DocumentPart extends EnumHelper[DocumentPart] {

  final case object RECTO extends DocumentPart
  final case object VERSO extends DocumentPart
  final case object OTHER extends DocumentPart

  val values = Set(RECTO, VERSO, OTHER)
}
