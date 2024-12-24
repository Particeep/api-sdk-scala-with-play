package com.particeep.api.models.enums

sealed trait FundraiseFormTag extends Product with Enum

object FundraiseFormTag extends EnumHelper[FundraiseFormTag] {

  case object NATURAL                       extends FundraiseFormTag
  case object LEGAL                         extends FundraiseFormTag
  case object SOPHISTICATED_NATURAL         extends FundraiseFormTag
  case object SOPHISTICATED_LEGAL           extends FundraiseFormTag
  case object EXECUTIVE                     extends FundraiseFormTag
  case object BENEFICIARY                   extends FundraiseFormTag
  case object SPOUSE                        extends FundraiseFormTag
  case object GUARDIAN                      extends FundraiseFormTag
  case object CURATOR                       extends FundraiseFormTag
  case object LEGAL_REPRESENTATIVE          extends FundraiseFormTag
  case object SECONDARY_MARKET_CREATION     extends FundraiseFormTag
  case object SECONDARY_MARKET_MODIFICATION extends FundraiseFormTag
  case object SECONDARY_MARKET_CANCELLATION extends FundraiseFormTag

  def values: Set[FundraiseFormTag] = Set(
    NATURAL,
    LEGAL,
    SOPHISTICATED_NATURAL,
    SOPHISTICATED_LEGAL,
    EXECUTIVE,
    BENEFICIARY,
    SPOUSE,
    GUARDIAN,
    CURATOR,
    LEGAL_REPRESENTATIVE,
    SECONDARY_MARKET_CREATION,
    SECONDARY_MARKET_MODIFICATION,
    SECONDARY_MARKET_CANCELLATION
  )
}
