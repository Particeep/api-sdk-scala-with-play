package com.particeep.api.models.fundraise.loan

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsArray, JsObject, Json }

case class LendCreation(
    user_id:    String,
    amount:     Int,
    co_issuers: Option[JsArray]        = None,
    created_at: Option[OffsetDateTime],
    comment:    Option[String]         = None,
    custom:     Option[JsObject]       = None
)

object LendCreation {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[LendCreation]
}
