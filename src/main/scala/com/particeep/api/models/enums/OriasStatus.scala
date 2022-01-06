package com.particeep.api.models.enums

import play.api.libs.json.{ JsString, JsValue, Writes }
import scala.language.implicitConversions

object OriasStatus {
  sealed abstract class OriasStatus extends Enum

  case object REGISTERED extends OriasStatus { val name: String = "INSCRIT" }
  case object DELETED extends OriasStatus { val name: String = "SUPPRIME" }
  case object NEVER_REGISTERED extends OriasStatus { val name: String = "JAMAIS_INSCRIT" }

  object OriasStatus extends EnumHelper[OriasStatus] {
    def values: Set[OriasStatus] = Set(REGISTERED, DELETED, NEVER_REGISTERED)

    implicit def stringToOriasStatus(value: Option[String]): OriasStatus = {
      get(value).getOrElse(NEVER_REGISTERED)
    }

    override implicit def enumWrites: Writes[OriasStatus] = new Writes[OriasStatus] {
      def writes(v: OriasStatus): JsValue = JsString(v.toString)
    }

    override def get(value: String): Option[OriasStatus] = values.find(t => t.toString == value)

    override def get(valueOpt: Option[String]): Option[OriasStatus] = values.find(t => t.name == valueOpt.getOrElse(""))
  }
}
