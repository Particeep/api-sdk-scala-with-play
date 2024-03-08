package com.particeep.api.models.enums

object KycStatus {

  sealed abstract class KycStatus extends Product with Enum { val order: Int }

  case object CREATED        extends KycStatus { val order = 1 }
  case object ASK_VALIDATION extends KycStatus { val order = 2 }
  case object VALIDATED      extends KycStatus { val order = 3 }
  case object REFUSED        extends KycStatus { val order = 4 }
  case object CANCELLED      extends KycStatus { val order = 5 }

  object KycStatus extends EnumHelper[KycStatus] {
    def values: Set[KycStatus] = Set(CREATED, ASK_VALIDATION, VALIDATED, REFUSED, CANCELLED)
  }
}
