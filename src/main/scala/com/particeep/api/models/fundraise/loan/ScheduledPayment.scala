package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class ScheduledPayment(
  id:                    String                 = "",
  created_at:            Option[OffsetDateTime] = None,
  payment_date:          OffsetDateTime,
  issuer_id:             String,
  issuer_type:           String,
  recipient_id:          String,
  recipient_type:        String,
  parent_id:             Option[String]         = None,
  parent_type:           Option[String]         = None,
  amount:                Int,
  fees:                  Int,
  status:                String,
  transaction_wallet_id: Option[String]         = None,
  tag:                   Option[String]         = None
)

object ScheduledPayment {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[ScheduledPayment]            = Json.format[ScheduledPayment]
}
