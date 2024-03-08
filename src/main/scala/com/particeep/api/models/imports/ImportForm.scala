package com.particeep.api.models.imports

import play.api.libs.json._

case class ImportForm(
  tag:    Option[String]   = None,
  custom: Option[JsObject] = None
)
