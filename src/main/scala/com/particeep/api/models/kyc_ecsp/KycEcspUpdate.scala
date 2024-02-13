package com.particeep.api.models.kyc_ecsp

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.JsObject

sealed trait KycEcspUpdate {
  def tag: Option[String]
  def custom: Option[JsObject]
}

object KycEcspUpdate {
  final case class Natural(
      is_pro:                     Option[Boolean]  = None,
      has_high_income:            Option[Boolean]  = None,
      has_done_many_transactions: Option[Boolean]  = None,
      annual_income:              Option[Long]     = None,
      amount_of_assets:           Option[Long]     = None,
      amount_of_debt:             Option[Long]     = None,
      tag:                        Option[String]   = None,
      custom:                     Option[JsObject] = None
  ) extends KycEcspUpdate

  final case class Legal(
      has_high_capital:       Option[Boolean]  = None, // FR : Capital de l'entreprise
      has_high_sales_figures: Option[Boolean]  = None, // FR : Chiffre d'affaire
      has_high_balance_sheet: Option[Boolean]  = None, // FR : Bilan comptable
      operating_income:       Option[Long]     = None,
      tag:                    Option[String]   = None,
      custom:                 Option[JsObject] = None
  ) extends KycEcspUpdate

  implicit val kyc_ecsp_natural_update_format = Jsonx.formatCaseClassUseDefaults[KycEcspUpdate.Natural]
  implicit val kyc_ecsp_legal_update_format = Jsonx.formatCaseClassUseDefaults[KycEcspUpdate.Legal]
  implicit val kyc_ecsp_update_format = Jsonx.formatSealed[KycEcspUpdate]
}