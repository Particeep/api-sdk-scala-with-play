package com.particeep.api.models.imports

import play.api.libs.json.{ Json, OFormat }

/**
 * Created by Noe on 10/04/2017.
 */
case class LineError(
  line:   Int,
  output: String
)

object LineError {
  val format: OFormat[LineError] = Json.format[LineError]
}
