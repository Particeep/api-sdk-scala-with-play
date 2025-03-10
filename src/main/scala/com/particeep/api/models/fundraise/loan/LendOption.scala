package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ JsArray, JsObject, Json, OFormat }

import com.particeep.api.models.enums.PaymentMethod.OfflinePaymentMethod
import com.particeep.api.models.transaction.CoIssuer

case class LendOption(
  amount:                 Option[Int]                  = None,
  partner_fees:           Option[Int]                  = None,
  unicia_id:              Option[String]               = None,
  comment:                Option[String]               = None,
  private_comment:        Option[String]               = None,
  tag:                    Option[String]               = None,
  co_issuers:             Option[JsArray]              = None,
  new_co_issuers:         Option[List[CoIssuer]]       = None,
  custom:                 Option[JsObject]             = None,
  offline_payment_method: Option[OfflinePaymentMethod] = None
)

object LendOption {
  val format: OFormat[LendOption] = Json.format[LendOption]
}
