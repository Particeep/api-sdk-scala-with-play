package com.particeep.api.models.enums

object KycType {

  sealed abstract class KycType extends Product with Enum

  case object ID_CARD                  extends KycType
  case object PASSPORT                 extends KycType
  case object RESIDENCE_PERMIT         extends KycType
  case object DRIVER_LICENSE           extends KycType
  case object ADDRESS_PROOF            extends KycType
  case object COMPANY_STATUS           extends KycType
  case object RIB                      extends KycType
  case object KBIS                     extends KycType
  case object TAX_STATUS               extends KycType
  case object PRESIDENT_OF_ASSOCIATION extends KycType
  case object OFFICIAL_JOURNAL         extends KycType
  case object ASSOCIATION_STATUS       extends KycType
  case object SHAREHOLDER_KYCS         extends KycType
  case object GENERAL_MEETING          extends KycType
  case object HEALTH_INSURANCE_CARD    extends KycType
  case object DESCRIPTION_PROJECT      extends KycType
  case object SHAREHOLDER_ID           extends KycType
  case object SHAREHOLDER_ID2          extends KycType
  case object SHAREHOLDER_ID3          extends KycType

  object KycType extends EnumHelper[KycType] {
    def values: Set[KycType] = Set(
      ID_CARD,
      PASSPORT,
      RESIDENCE_PERMIT,
      DRIVER_LICENSE,
      ADDRESS_PROOF,
      COMPANY_STATUS,
      RIB,
      KBIS,
      TAX_STATUS,
      PRESIDENT_OF_ASSOCIATION,
      OFFICIAL_JOURNAL,
      ASSOCIATION_STATUS,
      SHAREHOLDER_KYCS,
      GENERAL_MEETING,
      HEALTH_INSURANCE_CARD,
      DESCRIPTION_PROJECT,
      SHAREHOLDER_ID,
      SHAREHOLDER_ID2,
      SHAREHOLDER_ID3
    )
  }
}
