package com.particeep.api.models.enums

import play.api.data.format.Formatter
import play.api.data.{ FormError, Forms, Mapping }
import play.api.libs.json._

trait Enum { self: Product =>
  val name: String = this.productPrefix
}

trait EnumHelper[E <: Enum] {
  def values: Set[E]

  def get(value: String): Option[E] = values.find(t => t.name == value)

  def get(valueOpt: Option[String]): Option[E] = valueOpt.flatMap(get)

  def toString(value: E): String = value.name

  implicit def ordering: Ordering[E]                            = Ordering.by(_.name)
  implicit def enum2string(status: E): String                   = toString(status)
  implicit def string2enum(value: String): Option[E]            = get(value)
  implicit def optString2enum(value: Option[String]): Option[E] = get(value.getOrElse(""))

  implicit def enumReads: Reads[E] = (json: JsValue) =>
    json match {
      case JsString(s) => get(s) match {
          case Some(enum) => JsSuccess(enum)
          case None       =>
            JsError(s"[error] enum value $s is not in the enum possible value ${values.map(_.name).mkString(", ")}")
        }
      case _           => JsError(s"[error] unknown error while parsing value from json $json")
    }

  implicit def enumWrites: Writes[E] = (v: E) => JsString(v)

  val formatMapping: Mapping[E] = Forms.of(enumFormat)

  private[this] def enumFormat: Formatter[E] = new Formatter[E] {
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], E] = {
      data.get(key) match {
        case Some(value) => get(value).map(Right(_)).getOrElse(Left(List(FormError(key, "error.enum.not.found"))))
        case None        => Left(List(FormError(key, "error.enum.unknown")))
      }
    }

    def unbind(key: String, value: E): Map[String, String] = Map(key -> value.name)
  }
}
