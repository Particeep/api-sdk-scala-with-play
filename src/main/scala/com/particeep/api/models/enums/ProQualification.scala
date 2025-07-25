package com.particeep.api.models.enums

sealed trait ProQualification extends Product with Serializable with Enum

object ProQualification extends EnumHelper[ProQualification] {

  case object PROFESSIONAL          extends ProQualification
  case object NOT_PROFESSIONAL      extends ProQualification
  case object ELIGIBLE_COUNTERPARTY extends ProQualification

  def values: Set[ProQualification] =
    Set(PROFESSIONAL, NOT_PROFESSIONAL, ELIGIBLE_COUNTERPARTY)
}
