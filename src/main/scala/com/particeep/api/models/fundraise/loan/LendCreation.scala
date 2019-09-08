package com.particeep.api.models.fundraise.loan

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsArray, JsObject, Json }

case class LendCreation(
  user_id:    String,
  amount:     Int,
  co_issuers: Option[JsArray]       = None,
  created_at: Option[ZonedDateTime],
  comment:    Option[String]        = None,
  custom:     Option[JsObject]      = None
)

object LendCreation {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[LendCreation]
}
