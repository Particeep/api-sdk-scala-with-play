package com.particeep.api.models.fundraise.equity

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsArray, JsObject, Json }

case class InvestmentCreation(
  user_id:    String,
  amount:     Int,
  co_issuers: Option[JsArray]       = None,
  created_at: Option[ZonedDateTime],
  custom:     Option[JsObject]      = None
)

object InvestmentCreation {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[InvestmentCreation]
}
