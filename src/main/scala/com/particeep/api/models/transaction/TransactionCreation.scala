package com.particeep.api.models.transaction

import play.api.libs.json.{JsArray, JsObject, Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.{ Currency, EUR }

/**
 * Created by Noe on 03/04/2017.
 */
case class TransactionCreation(
    created_at:      Option[OffsetDateTime] = None,
    issuer_id:       String                 = "",
    issuer_type:     String                 = "",
    recipient_id:    String                 = "",
    recipient_type:  String                 = "",
    fundraise_id:    Option[String]         = None,
    fundraise_type:  Option[String]         = None,
    item_id:         Option[String]         = None,
    item_type:       Option[String]         = None,
    amount:          Int                    = 0,
    fees:            Int                    = 0,
    partner_fees:    Option[Int]            = None,
    currency:        Currency               = EUR,
    unicia_id:       Option[String]         = None,
    comment:         Option[String]         = None,
    private_comment: Option[String]         = None,
    tag:             Option[String]         = None,
    co_issuers:      Option[JsArray]        = None,
    custom:          Option[JsObject]       = None
)

object TransactionCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[TransactionCreation]
}
