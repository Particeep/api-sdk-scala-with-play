package com.particeep.api.models.payment

import play.api.libs.json.{Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class ScheduledPaymentCreation(
    payment_date:   OffsetDateTime,
    issuer_id:      String,
    issuer_type:    String,
    recipient_id:   String,
    recipient_type: String,
    parent_id:      Option[String] = None,
    parent_type:    Option[String] = None,
    amount:         Int,
    fees:           Int,
    tag:            Option[String] = None
)

object ScheduledPaymentCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[ScheduledPaymentCreation]
}
