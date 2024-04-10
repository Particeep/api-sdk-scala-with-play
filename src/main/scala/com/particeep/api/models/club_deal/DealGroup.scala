package com.particeep.api.models.club_deal

import play.api.libs.json.{ JsObject, Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

/**
 * Created by Noe on 04/07/2017.
 */
case class DealGroup(
  id:          String                 = "",
  created_at:  Option[OffsetDateTime] = None,
  name:        String                 = "",
  target_id:   String                 = "",
  target_type: String                 = "",
  open:        Boolean                = false,
  tag:         Option[String]         = None,
  custom:      Option[JsObject]       = None
)

object DealGroup {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[DealGroup]
}
