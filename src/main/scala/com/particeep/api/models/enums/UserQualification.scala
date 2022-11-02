package com.particeep.api.models.enums

sealed trait UserQualification extends Product with Serializable with Enum

object UserQualification extends EnumHelper[UserQualification] {

  case object PROFESSIONAL extends UserQualification
  case object SOPHISTICATED extends UserQualification
  case object NO_SOPHISTICATED extends UserQualification

  def values: Set[UserQualification] = Set(PROFESSIONAL, SOPHISTICATED, NO_SOPHISTICATED)
}
