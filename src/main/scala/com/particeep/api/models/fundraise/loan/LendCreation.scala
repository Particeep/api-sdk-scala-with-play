package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ JsArray, JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.PaymentMethod.OfflinePaymentMethod
import com.particeep.api.models.transaction.CoIssuer

case class LendCreation(
  user_id:                String,
  amount:                 Int,
  co_issuers:             Option[JsArray]              = None,
  new_co_issuers:         Option[List[CoIssuer]]       = None,
  created_at:             Option[OffsetDateTime],
  comment:                Option[String]               = None,
  custom:                 Option[JsObject]             = None,
  offline_payment_method: Option[OfflinePaymentMethod] = None
)

object LendCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[LendCreation]                = Json.format[LendCreation]
}
