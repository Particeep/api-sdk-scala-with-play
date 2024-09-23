package com.particeep.api.models.enums

sealed trait Gender extends Product with Enum

object Gender extends EnumHelper[Gender] {
  case object MAN   extends Gender
  case object WOMAN extends Gender

  def values: Set[Gender] = Set(MAN, WOMAN)

  implicit def string2gender(value: String): Gender = get(value).getOrElse(MAN)
}
