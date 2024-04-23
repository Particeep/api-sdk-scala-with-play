package com.particeep.api.models.signature

import play.api.libs.json.{ Json, OFormat }

case class SignatureSigner(
  firstName:  String,
  lastName:   String,
  email:      String,
  phone:      String,
  successURL: Option[String] = None,
  cancelURL:  Option[String] = None,
  failURL:    Option[String] = None
)

object SignatureSigner {
  val format: OFormat[SignatureSigner] = Json.format[SignatureSigner]
}
