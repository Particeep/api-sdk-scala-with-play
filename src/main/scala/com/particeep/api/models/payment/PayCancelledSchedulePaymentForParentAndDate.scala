package com.particeep.api.models.payment

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class PayCancelledSchedulePaymentForParentAndDate(
    parent_id: String,
    date:      OffsetDateTime
)

object PayCancelledSchedulePaymentForParentAndDate {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[PayCancelledSchedulePaymentForParentAndDate]
}
