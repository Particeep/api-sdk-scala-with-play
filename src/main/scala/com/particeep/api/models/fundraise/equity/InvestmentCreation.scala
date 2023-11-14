package com.particeep.api.models.fundraise.equity

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.PaymentMethod.OfflinePaymentMethod
import play.api.libs.json.{ JsArray, JsObject, Json }

case class InvestmentCreation(
    user_id:                String,
    amount:                 Int,
    check_required_pro:     Option[Boolean]              = None,
    co_issuers:             Option[JsArray]              = None,
    created_at:             Option[OffsetDateTime]       = None,
    dismemberment_duration: Option[Int]                  = None,
    dismemberment_rate:     Option[Double]               = None,
    comment:                Option[String]               = None,
    custom:                 Option[JsObject]             = None,
    offline_payment_method: Option[OfflinePaymentMethod] = None
)

object InvestmentCreation {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[InvestmentCreation]
}
