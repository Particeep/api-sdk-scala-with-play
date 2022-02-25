package com.particeep.api.models.enums

object ImportType {

  sealed abstract class ImportType extends Product with Enum

  case object USER extends ImportType
  case object ENTERPRISE extends ImportType
  case object FUNDRAISE_REWARD extends ImportType
  case object FUNDRAISE_LOAN extends ImportType
  case object FUNDRAISE_EQUITY extends ImportType
  case object TRANSACTION extends ImportType
  case object ANSWER_QUESTION extends ImportType
  case object DOCUMENT extends ImportType
  case object PARTNER_COMPANY extends ImportType

  object ImportType extends EnumHelper[ImportType] {
    def values: Set[ImportType] = Set(USER, ENTERPRISE, FUNDRAISE_REWARD, FUNDRAISE_LOAN, FUNDRAISE_EQUITY, TRANSACTION, ANSWER_QUESTION, DOCUMENT, PARTNER_COMPANY)
  }
}
