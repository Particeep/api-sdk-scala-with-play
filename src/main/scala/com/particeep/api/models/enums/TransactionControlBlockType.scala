package com.particeep.api.models.enums

sealed trait TransactionControlBlockType extends ControlBlockType

object TransactionControlBlockType extends EnumHelper[TransactionControlBlockType] {

  case object INVESTOR extends TransactionControlBlockType
  case object CO_ISSUER extends TransactionControlBlockType
  case object PARTNER extends TransactionControlBlockType
  case object USUFRUCTUARY extends TransactionControlBlockType
  case object BANK_ACCOUNT extends TransactionControlBlockType
  case object TRANSACTION extends TransactionControlBlockType
  case object DOCUMENTS extends TransactionControlBlockType
  case object QUESTION extends TransactionControlBlockType

  val values: Set[TransactionControlBlockType] = {
    Set(
      INVESTOR,
      CO_ISSUER,
      PARTNER,
      USUFRUCTUARY,
      BANK_ACCOUNT,
      TRANSACTION,
      DOCUMENTS,
      QUESTION
    )
  }

}
