package com.particeep.api.models.payment

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.Locale

case class PaymentCbCreation(
  accept_url:  String,
  decline_url: String,
  pending_url: String,
  owner_ip:    String,
  locale:      Option[Locale] = None
)

object PaymentCbCreation {
  val format: OFormat[PaymentCbCreation] = Json.format[PaymentCbCreation]
}
