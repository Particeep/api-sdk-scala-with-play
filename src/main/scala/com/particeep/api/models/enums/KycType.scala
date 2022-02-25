package com.particeep.api.models.enums

object KycType {

  sealed abstract class KycType extends Product with Enum

  case object ID_CARD extends KycType
  case object PASSPORT extends KycType
  case object RESIDENCE_PERMIT extends KycType
  case object DRIVER_LICENSE extends KycType
  case object ADDRESS_PROOF extends KycType
  case object COMPANY_STATUS extends KycType
  case object RIB extends KycType
  case object KBIS extends KycType
  case object TAX_STATUS extends KycType
  case object PRESIDENT_OF_ASSOCIATION extends KycType
  case object OFFICIAL_JOURNAL extends KycType
  case object ASSOCIATION_STATUS extends KycType
  case object SHAREHOLDER_KYCS extends KycType
  case object GENERAL_MEETING extends KycType
  case object HEALTH_INSURANCE_CARD extends KycType

  object KycType extends EnumHelper[KycType] {
    def values: Set[KycType] = Set(ID_CARD, PASSPORT, RESIDENCE_PERMIT, DRIVER_LICENSE, ADDRESS_PROOF, COMPANY_STATUS, RIB, KBIS, TAX_STATUS, PRESIDENT_OF_ASSOCIATION, OFFICIAL_JOURNAL, ASSOCIATION_STATUS, SHAREHOLDER_KYCS, GENERAL_MEETING, HEALTH_INSURANCE_CARD)
  }

  def parseFromHipay(kycType: String): Option[KycType] = {
    kycType match {
      case "1" | "3" | "7" => Some(ID_CARD)
      case "2"             => Some(ADDRESS_PROOF)
      case "4" | "8"       => Some(KBIS)
      case "5"             => Some(COMPANY_STATUS)
      case "6"             => Some(RIB)
      case "9"             => Some(TAX_STATUS)
      case "11"            => Some(PRESIDENT_OF_ASSOCIATION)
      case "12"            => Some(OFFICIAL_JOURNAL)
      case "13"            => Some(ASSOCIATION_STATUS)
      case _               => None
    }
  }
}
