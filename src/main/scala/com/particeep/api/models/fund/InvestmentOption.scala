package com.particeep.api.models.fund

import play.api.libs.json.{JsArray, JsObject, Json, OFormat}

case class InvestmentOption(
  amount:          Option[Int]      = None,
  partner_fees:    Option[Int]      = None,
  unicia_id:       Option[String]   = None,
  comment:         Option[String]   = None,
  private_comment: Option[String]   = None,
  tag:             Option[String]   = None,
  co_issuers:      Option[JsArray]  = None,
  custom:          Option[JsObject] = None
)

object InvestmentOption {
  val format: OFormat[InvestmentOption] = Json.format[InvestmentOption]
}
