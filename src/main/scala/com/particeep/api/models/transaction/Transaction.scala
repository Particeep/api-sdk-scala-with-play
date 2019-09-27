package com.particeep.api.models.transaction

import java.time.ZonedDateTime

import ai.x.play.json.Jsonx
import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.{ Currency, EUR }
import com.particeep.api.models.enums.PaymentMethod.PaymentMethod
import com.particeep.api.models.enums.TransactionStatus.{ PENDING, TransactionStatus }
import play.api.libs.json.{ JsArray, JsObject }

case class Transaction(
  id:                     String                = "",
  created_at:             Option[ZonedDateTime] = None,
  issuer_id:              String                = "",
  issuer_type:            String                = "",
  recipient_id:           String                = "",
  recipient_type:         String                = "",
  fundraise_id:           Option[String]        = None,
  fundraise_type:         Option[String]        = None,
  item_id:                Option[String]        = None,
  item_type:              Option[String]        = None,
  amount:                 Int                   = 0,
  fees:                   Int                   = 0,
  currency:               Currency              = EUR,
  status:                 TransactionStatus     = PENDING,
  payment_method:         Option[PaymentMethod] = None,
  handled_offline:        Option[Boolean]       = None,
  comment:                Option[String]        = None,
  tag:                    Option[String]        = None,
  co_issuers:             Option[JsArray]       = None,
  rib:                    Option[String]        = None,
  dismemberment_duration: Option[Int]           = None,
  dismemberment_rate:     Option[Double]        = None,
  custom:                 Option[JsObject]      = None
)

object Transaction {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Jsonx.formatCaseClass[Transaction]
}
