package com.particeep.api.models.enums

sealed trait ProQualification extends Product with Serializable with Enum

object ProQualification extends EnumHelper[ProQualification] {

  case object PROFESSIONAL extends ProQualification
  case object SOPHISTICATED extends ProQualification
  case object NOT_SOPHISTICATED extends ProQualification

  def values: Set[ProQualification] = Set(PROFESSIONAL, SOPHISTICATED, NOT_SOPHISTICATED)
}
