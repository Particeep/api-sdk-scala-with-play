package com.particeep.api.models.imports

import play.api.libs.json.JsObject

case class ImportForm(
  tag:    Option[String]   = None,
  custom: Option[JsObject] = None
)
