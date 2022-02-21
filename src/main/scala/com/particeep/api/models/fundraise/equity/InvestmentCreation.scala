package com.particeep.api.models.fundraise.equity

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsArray, JsObject, Json }

case class InvestmentCreation(
    user_id:                String,
    amount:                 Int,
    check_required_pro:     Option[Boolean]       = None,
    co_issuers:             Option[JsArray]       = None,
    co_issuers_legal:       Option[JsArray]       = None,
    created_at:             Option[ZonedDateTime] = None,
    dismemberment_duration: Option[Int]           = None,
    dismemberment_rate:     Option[Double]        = None,
    comment:                Option[String]        = None,
    custom:                 Option[JsObject]      = None
)

object InvestmentCreation {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[InvestmentCreation]
}
