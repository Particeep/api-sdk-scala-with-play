package com.particeep.api.models.imports

import play.api.libs.json._

/**
 * Created by Noe on 10/04/2017.
 */
case class LineError(
  line:   Int,
  output: String
)

object LineError {
  val format = Json.format[LineError]
}
