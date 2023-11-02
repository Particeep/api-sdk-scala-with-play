package com.particeep.api.models.transaction

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.Currency
import com.particeep.api.models.enums.PaymentMethod
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import play.api.libs.json.{ JsArray, JsObject }

/**
 * Created by Noe on 30/06/2017.
 */
case class TransactionData(
    id:                                    String,
    created_at:                            Option[OffsetDateTime] = None,
    issuer_id:                             Option[String]         = None,
    issuer_type:                           Option[String]         = None,
    issuer_name:                           Option[String]         = None,
    recipient_id:                          Option[String]         = None,
    recipient_type:                        Option[String]         = None,
    recipient_name:                        Option[String]         = None,
    fundraise_id:                          Option[String]         = None,
    fundraise_type:                        Option[String]         = None,
    item_id:                               Option[String]         = None,
    item_type:                             Option[String]         = None,
    amount:                                Option[Int]            = None,
    fees:                                  Option[Int]            = None,
    partner_fees:                          Option[Int]            = None,
    currency:                              Option[Currency]       = None,
    status:                                Option[String]         = None,
    control_status:                        Option[String]         = None,
    payment_method:                        Option[PaymentMethod]  = None,
    external_reference:                    Option[String]         = None,
    handled_offline:                       Option[Boolean]        = None,
    comment:                               Option[String]         = None,
    targeting_roles:                       Option[String]         = None,
    partner_flat_fees:                     Option[Int]            = None,
    partner_variable_fees:                 Option[Double]         = None,
    partner_manager_id:                    Option[String]         = None,
    partner_manager_company_business_name: Option[String]         = None,
    signature_ids:                         Option[String]         = None,
    signature_status:                      Option[String]         = None,
    tag:                                   Option[String]         = None,
    co_issuers:                            Option[JsArray]        = None,
    rib:                                   Option[String]         = None,
    price_per_share:                       Option[Int]            = None,
    pre_share_fixing_amount:               Option[Int]            = None,
    pre_share_fixing_fees:                 Option[Int]            = None,
    dismemberment_duration:                Option[Int]            = None,
    dismemberment_rate:                    Option[Double]         = None,
    bare_owner_amount:                     Option[Int]            = None,
    usufructuary_amount:                   Option[Int]            = None,
    custom:                                Option[JsObject]       = None
)

object TransactionData {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Jsonx.formatCaseClass[TransactionData]
}
