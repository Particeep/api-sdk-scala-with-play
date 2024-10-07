package com.particeep.api.models.enums

import play.api.libs.json.{ JsString, Writes }

sealed trait OriasStatus extends Product with Enum

object OriasStatus extends EnumHelper[OriasStatus] {
  case object REGISTERED       extends OriasStatus
  case object DELETED          extends OriasStatus
  case object NEVER_REGISTERED extends OriasStatus

  def values: Set[OriasStatus] = Set(REGISTERED, DELETED, NEVER_REGISTERED)

  implicit def stringToOriasStatus(value: Option[String]): OriasStatus = {
    get(value).getOrElse(NEVER_REGISTERED)
  }

  override implicit def enumWrites: Writes[OriasStatus] = (v: OriasStatus) => JsString(v.toString)

  override def get(value: String): Option[OriasStatus] = values.find(t => t.toString == value)

  override def get(valueOpt: Option[String]): Option[OriasStatus] = values.find(t => t.name == valueOpt.getOrElse(""))
}
