package com.particeep.api.models.club_deal

import play.api.libs.json.{ JsObject, Json, OFormat }

/**
 * Created by Noe on 04/07/2017.
 */
case class DealGroupCreation(
  name:        String,
  target_id:   String,
  target_type: String,
  tag:         Option[String]   = None,
  custom:      Option[JsObject] = None
)

object DealGroupCreation {
  val format: OFormat[DealGroupCreation] = Json.format[DealGroupCreation]
}
