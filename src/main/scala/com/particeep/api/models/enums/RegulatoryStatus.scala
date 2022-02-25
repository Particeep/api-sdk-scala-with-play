package com.particeep.api.models.enums

object RegulatoryStatus {
  sealed abstract class RegulatoryStatus extends Product with Enum

  case object CIF extends RegulatoryStatus
  case object ALPSI extends RegulatoryStatus
  case object PSI extends RegulatoryStatus
  case object SDG extends RegulatoryStatus

  object RegulatoryStatus extends EnumHelper[RegulatoryStatus] {
    def values: Set[RegulatoryStatus] = Set(CIF, ALPSI, PSI, SDG)
  }
}
