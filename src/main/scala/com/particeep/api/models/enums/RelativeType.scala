package com.particeep.api.models.enums

object RelativeType {
  sealed abstract class RelativeType extends Enum
  case object CONJOINT extends RelativeType { val name: String = "CONJOINT" }
  case object USUFRUCTUARY extends RelativeType { val name: String = "USUFRUCTUARY" }
  case object PAYER extends RelativeType { val name: String = "PAYER" }

  object RelativeType extends EnumHelper[RelativeType] {
    def values: Set[RelativeType] = Set(CONJOINT, USUFRUCTUARY, PAYER)

    def jsonToString = {
      val s = values.map { v =>
        s""""$v""""
      }.mkString(",")
      s"[$s]"
    }
  }
}
