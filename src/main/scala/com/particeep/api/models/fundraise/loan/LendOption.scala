package com.particeep.api.models.fundraise.loan

import com.particeep.api.models.enums.PaymentMethod.OfflinePaymentMethod
import play.api.libs.json.{ JsArray, JsObject, Json }

case class LendOption(
    amount:                 Option[Int]                  = None,
    partner_fees:           Option[Int]                  = None,
    unicia_id:              Option[String]               = None,
    comment:                Option[String]               = None,
    private_comment:        Option[String]               = None,
    tag:                    Option[String]               = None,
    co_issuers:             Option[JsArray]              = None,
    rib:                    Option[String]               = None,
    custom:                 Option[JsObject]             = None,
    offline_payment_method: Option[OfflinePaymentMethod] = None
)

object LendOption {
  val format = Json.format[LendOption]
}
