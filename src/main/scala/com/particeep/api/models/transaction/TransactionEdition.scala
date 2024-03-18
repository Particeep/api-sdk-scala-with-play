package com.particeep.api.models.transaction

import com.particeep.api.models.enums.Currency.Currency
import com.particeep.api.models.enums.PaymentMethod.OfflinePaymentMethod
import play.api.libs.json.{ JsArray, JsObject, Json }

case class TransactionEdition(
    issuer_id:              Option[String]               = None,
    issuer_type:            Option[String]               = None,
    recipient_id:           Option[String]               = None,
    recipient_type:         Option[String]               = None,
    fundraise_id:           Option[String]               = None,
    fundraise_type:         Option[String]               = None,
    item_id:                Option[String]               = None,
    item_type:              Option[String]               = None,
    amount:                 Option[Int]                  = None,
    fees:                   Option[Int]                  = None,
    partner_fees:           Option[Int]                  = None,
    currency:               Option[Currency]             = None,
    unicia_id:              Option[String]               = None,
    handled_offline:        Option[Boolean]              = None,
    comment:                Option[String]               = None,
    private_comment:        Option[String]               = None,
    tag:                    Option[String]               = None,
    co_issuers:             Option[JsArray]              = None,
    custom:                 Option[JsObject]             = None,
    offline_payment_method: Option[OfflinePaymentMethod] = None
)

object TransactionEdition {
  val format = Json.format[TransactionEdition]
}
