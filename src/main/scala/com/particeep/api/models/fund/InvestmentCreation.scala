package com.particeep.api.models.fund

import play.api.libs.json.{ JsArray, JsObject, Json, OFormat }

import com.particeep.api.models.enums.PaymentMethod.OfflinePaymentMethod
import com.particeep.api.models.transaction.CoIssuer

case class InvestmentCreation(
  user_id:                String,
  amount:                 Int,
  check_required_pro:     Option[Boolean]              = None,
  co_issuers:             Option[JsArray]              = None,
  new_co_issuers:         Option[List[CoIssuer]]       = None,
  custom:                 Option[JsObject]             = None,
  partner_fees:           Option[Double]               = None,
  comment:                Option[String]               = None,
  offline_payment_method: Option[OfflinePaymentMethod] = None
)

object InvestmentCreation {
  val format: OFormat[InvestmentCreation] = Json.format[InvestmentCreation]
}
