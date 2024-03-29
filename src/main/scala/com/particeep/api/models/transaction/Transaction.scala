package com.particeep.api.models.transaction

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.{ Currency, EUR }
import com.particeep.api.models.enums.PaymentMethod
import com.particeep.api.models.enums.TransactionStatus.{ PENDING, TransactionStatus }
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import play.api.libs.json.{ JsArray, JsObject, Writes }

case class Transaction(
    id:                      String                 = "",
    created_at:              Option[OffsetDateTime] = None,
    succeeded_at:            Option[OffsetDateTime] = None,
    issuer_id:               String                 = "",
    issuer_type:             String                 = "",
    recipient_id:            String                 = "",
    recipient_type:          String                 = "",
    fundraise_id:            Option[String]         = None,
    fundraise_type:          Option[String]         = None,
    item_id:                 Option[String]         = None,
    item_type:               Option[String]         = None,
    amount:                  Int                    = 0,
    fees:                    Int                    = 0,
    partner_fees:            Option[Int]            = None,
    currency:                Currency               = EUR,
    status:                  TransactionStatus      = PENDING,
    payment_method:          Option[PaymentMethod]  = None,
    unicia_id:               Option[String]         = None,
    handled_offline:         Option[Boolean]        = None,
    comment:                 Option[String]         = None,
    private_comment:         Option[String]         = None,
    tag:                     Option[String]         = None,
    co_issuers:              Option[JsArray]        = None,
    price_per_bond:          Option[Int]            = None,
    price_per_share:         Option[Int]            = None,
    pre_share_fixing_amount: Option[Int]            = None,
    pre_share_fixing_fees:   Option[Int]            = None,
    dismemberment_duration:  Option[Int]            = None,
    dismemberment_rate:      Option[Double]         = None,
    bare_owner_amount:       Option[Int]            = None,
    usufructuary_amount:     Option[Int]            = None,
    custom:                  Option[JsObject]       = None
)

object Transaction {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Jsonx.formatCaseClass[Transaction]
}
