package com.particeep.api.models.enums

object ImportType {

  sealed abstract class ImportType extends Enum

  case object USER extends ImportType { val name: String = "USER" }
  case object ENTERPRISE extends ImportType { val name: String = "ENTERPRISE" }
  case object FUNDRAISE_REWARD extends ImportType { val name: String = "FUNDRAISE_REWARD" }
  case object FUNDRAISE_LOAN extends ImportType { val name: String = "FUNDRAISE_LOAN" }
  case object FUNDRAISE_EQUITY extends ImportType { val name: String = "FUNDRAISE_EQUITY" }
  case object TRANSACTION extends ImportType { val name: String = "TRANSACTION" }
  case object ANSWER_QUESTION extends ImportType { val name: String = "ANSWER_QUESTION" }
  case object DOCUMENT extends ImportType { val name: String = "DOCUMENT" }

  object ImportType extends EnumHelper[ImportType] {
    def values: Set[ImportType] = Set(USER, ENTERPRISE, FUNDRAISE_REWARD, FUNDRAISE_LOAN, FUNDRAISE_EQUITY, TRANSACTION, ANSWER_QUESTION, DOCUMENT)
  }
}
