package com.particeep.api.models.fund

import play.api.libs.json.{ JsArray, JsObject, Json, OFormat }

import com.particeep.api.models.user.relative.RelativeMetaData

case class InvestmentOption(
  amount:          Option[Int]                    = None,
  partner_fees:    Option[Int]                    = None,
  unicia_id:       Option[String]                 = None,
  comment:         Option[String]                 = None,
  private_comment: Option[String]                 = None,
  tag:             Option[String]                 = None,
  co_issuers:      Option[JsArray]                = None,
  new_co_issuers:  Option[List[RelativeMetaData]] = None,
  custom:          Option[JsObject]               = None
)

object InvestmentOption {
  val format: OFormat[InvestmentOption] = Json.format[InvestmentOption]
}
