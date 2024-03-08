package com.particeep.api.models.document

import com.particeep.api.models.enums.{ Enum, EnumHelper }

sealed trait Scope extends Product with Serializable with Enum

object Scope extends EnumHelper[Scope] {

  case object PUBLIC  extends Scope { override val name: String = "public"  }
  case object PRIVATE extends Scope { override val name: String = "private" }

  val values: Set[Scope] = Set(PUBLIC, PRIVATE)
}
