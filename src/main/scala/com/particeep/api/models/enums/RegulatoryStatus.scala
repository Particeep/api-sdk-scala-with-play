package com.particeep.api.models.enums

object RegulatoryStatus {
  sealed abstract class RegulatoryStatus extends Product with Enum

  case object CIF extends RegulatoryStatus

  case object CIF2 extends RegulatoryStatus

  case object SGP extends RegulatoryStatus

  case object PSI extends RegulatoryStatus

  case object GROUP extends RegulatoryStatus

  case object DISTRIBUTOR extends RegulatoryStatus

  case object ALPSI extends RegulatoryStatus

  object RegulatoryStatus extends EnumHelper[RegulatoryStatus] {
    def values: Set[RegulatoryStatus] = Set(CIF, CIF2, SGP, PSI, GROUP, DISTRIBUTOR, ALPSI)
  }
}
