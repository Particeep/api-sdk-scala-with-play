package com.particeep.api.models.payment

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class PayCancelledSchedulePaymentForParentAndDate(
    parent_id: String,
    date:      ZonedDateTime
)

object PayCancelledSchedulePaymentForParentAndDate {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[PayCancelledSchedulePaymentForParentAndDate]
}
