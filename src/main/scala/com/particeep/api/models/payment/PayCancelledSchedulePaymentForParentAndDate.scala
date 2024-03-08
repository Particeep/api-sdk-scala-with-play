package com.particeep.api.models.payment

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class PayCancelledSchedulePaymentForParentAndDate(
  parent_id: String,
  date:      OffsetDateTime
)

object PayCancelledSchedulePaymentForParentAndDate {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[PayCancelledSchedulePaymentForParentAndDate]
}
