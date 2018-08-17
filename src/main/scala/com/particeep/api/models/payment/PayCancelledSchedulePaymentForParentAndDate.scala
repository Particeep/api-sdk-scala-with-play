package com.particeep.api.models.payment

import java.time.ZonedDateTime

import play.api.libs.json.Json

case class PayCancelledSchedulePaymentForParentAndDate(
  parent_id: String,
  date:      ZonedDateTime
)

object PayCancelledSchedulePaymentForParentAndDate {
  val format = Json.format[PayCancelledSchedulePaymentForParentAndDate]
}
