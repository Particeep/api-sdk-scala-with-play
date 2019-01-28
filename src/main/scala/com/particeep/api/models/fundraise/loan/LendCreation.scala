package com.particeep.api.models.fundraise.loan

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsArray, Json }

case class LendCreation(
  user_id:    String,
  amount:     Int,
  rib:        Option[String]        = None,
  co_issuers: Option[JsArray]       = None,
  created_at: Option[ZonedDateTime]
)

object LendCreation {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[LendCreation]
}
