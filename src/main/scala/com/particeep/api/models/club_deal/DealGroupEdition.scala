package com.particeep.api.models.club_deal

import play.api.libs.json.{ JsObject, Json, OFormat }

/**
 * Created by Noe on 04/07/2017.
 */
case class DealGroupEdition(
  name:   Option[String]   = None,
  tag:    Option[String]   = None,
  custom: Option[JsObject] = None
)

object DealGroupEdition {
  val format: OFormat[DealGroupEdition] = Json.format[DealGroupEdition]
}
