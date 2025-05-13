package com.particeep.api.models.enums

import play.api.libs.json._
import slick.jdbc.PostgresProfile.api._

import scala.reflect.ClassTag

trait NewEnum { self: Product =>
  def name = this.productPrefix
}

trait NewEnumHelper[E <: NewEnum] {
  def values: Set[E]

  def get(value: String): Option[E] = values.filter(t => t.name == value).headOption

  def get(valueOpt: Option[String]): Option[E] = valueOpt.flatMap(get(_))

  def toString(value: E) = value.name

  implicit def enumReads: Reads[E] = new Reads[E] {
    def reads(json: JsValue): JsResult[E] = json match {
      case JsString(s) => get(s) match {
          case Some(enum) => JsSuccess(enum)
          case None       =>
            JsError(s"[error] enum value $s is not in the enum possible value ${values.map(_.name).mkString(", ")}")
        }
      case _           => JsError(s"[error] unknown error while parsing value from json $json")
    }
  }

  implicit def enumWrites: Writes[E] = new Writes[E] {
    def writes(v: E): JsValue = JsString(v.name)
  }
}

trait NewEnumSlickHelper[E <: NewEnum] extends NewEnumHelper[E] {

  implicit def tag(implicit m: ClassTag[E]): BaseColumnType[E] = {
    implicit def myEnumMapper = MappedColumnType.base[E, String](
      e => e.toString,
      s => get(s).get
    )
    myEnumMapper
  }
}
