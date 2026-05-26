package com.particeep.api.models.enums

sealed trait TransactionCreatorType  extends Product with Enum

object TransactionCreatorType  extends EnumHelper[TransactionCreatorType ] {

  case object PARTNER   extends TransactionCreatorType
  case object INVESTOR    extends TransactionCreatorType

  def values: Set[TransactionCreatorType ] = Set(PARTNER, INVESTOR)
}