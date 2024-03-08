package com.particeep.api.models.payment

import play.api.libs.json._

import com.particeep.api.models.enums.Locale.Locale

case class PaymentCbCreation(
  accept_url:  String,
  decline_url: String,
  pending_url: String,
  owner_ip:    String,
  locale:      Option[Locale] = None
)

object PaymentCbCreation {
  val format = Json.format[PaymentCbCreation]
}
