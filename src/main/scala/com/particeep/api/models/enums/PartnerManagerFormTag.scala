package com.particeep.api.models.enums

sealed trait PartnerManagerFormTag extends Product with Enum

object PartnerManagerFormTag extends EnumHelper[PartnerManagerFormTag] {

  case object NATURAL               extends PartnerManagerFormTag
  case object LEGAL                 extends PartnerManagerFormTag
  case object CGP                   extends PartnerManagerFormTag
  case object ENTREPRENEUR          extends PartnerManagerFormTag
  case object SOPHISTICATED_NATURAL extends PartnerManagerFormTag
  case object SOPHISTICATED_LEGAL   extends PartnerManagerFormTag
  case object EXECUTIVE             extends PartnerManagerFormTag
  case object BENEFICIARY           extends PartnerManagerFormTag
  case object SPOUSE                extends PartnerManagerFormTag
  case object GUARDIAN              extends PartnerManagerFormTag
  case object CURATOR               extends PartnerManagerFormTag
  case object LEGAL_REPRESENTATIVE  extends PartnerManagerFormTag

  def values: Set[PartnerManagerFormTag] = Set(
    NATURAL,
    LEGAL,
    CGP,
    ENTREPRENEUR,
    SOPHISTICATED_NATURAL,
    SOPHISTICATED_LEGAL,
    EXECUTIVE,
    BENEFICIARY,
    SPOUSE,
    GUARDIAN,
    CURATOR,
    LEGAL_REPRESENTATIVE
  )
}
