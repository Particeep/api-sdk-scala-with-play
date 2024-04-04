package com.particeep.api.models.kyc_ecsp

import java.time.OffsetDateTime
import play.api.libs.json.{ JsObject }

sealed trait KycEcsp {
  def id: String
  def created_at: OffsetDateTime
  def user_id: String
  def updated_at: Option[OffsetDateTime]
  def validated_at: Option[OffsetDateTime]
  def is_sophisticated: Boolean
  def net_worth: Long
  def investment_advice_threshold: Long
  def tag: Option[String]
  def custom: Option[JsObject]
}

object KycEcsp {
  final case class Natural(
      id:                          String,
      created_at:                  OffsetDateTime,
      user_id:                     String,
      updated_at:                  Option[OffsetDateTime] = None,
      validated_at:                Option[OffsetDateTime] = None,
      is_pro:                      Boolean                = false,
      has_high_income:             Boolean                = false,
      has_done_many_transactions:  Boolean                = false,
      annual_income:               Option[Long]           = None,
      amount_of_assets:            Option[Long]           = None,
      amount_of_debt:              Option[Long]           = None,
      is_sophisticated:            Boolean                = false,
      net_worth:                   Long                   = 0L,
      investment_advice_threshold: Long                   = 0L,
      tag:                         Option[String]         = None,
      custom:                      Option[JsObject]       = None
  ) extends KycEcsp

  final case class Legal(
      id:                          String,
      created_at:                  OffsetDateTime,
      user_id:                     String,
      updated_at:                  Option[OffsetDateTime] = None,
      validated_at:                Option[OffsetDateTime] = None,
      has_high_capital:            Boolean                = false,
      has_high_sales_figures:      Boolean                = false,
      has_high_balance_sheet:      Boolean                = false,
      operating_income:            Option[Long]           = None,
      is_sophisticated:            Boolean                = false,
      net_worth:                   Long                   = 0L,
      investment_advice_threshold: Long                   = 0L,
      tag:                         Option[String]         = None,
      custom:                      Option[JsObject]       = None
  ) extends KycEcsp
}