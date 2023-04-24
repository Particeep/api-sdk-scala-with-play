package com.particeep.api.models.fundraise.loan

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.Currency.{ Currency, EUR }
import com.particeep.api.models.enums.FundraiseStatus.{ FundraiseStatus, INIT }
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import play.api.libs.json.JsObject

import java.time.OffsetDateTime

case class FundraiseLoan(
    id:                    String                 = "",
    created_at:            Option[OffsetDateTime] = None,
    enterprise_id:         Option[String]         = None,
    recipient_id:          Option[String]         = None,
    recipient_type:        Option[String]         = None,
    name:                  String                 = "",
    description_short:     Option[String]         = None,
    description_long:      Option[String]         = None,
    description_offline:   Option[String]         = None,
    description_financial: Option[String]         = None,
    disclaimer_risk:       Option[String]         = None,
    disclaimer_fees:       Option[String]         = None,
    disclaimer_payment:    Option[String]         = None,
    start_at:              Option[OffsetDateTime] = None,
    end_at:                Option[OffsetDateTime] = None,
    amount_target:         Long                   = 0,
    amount_target_max:     Option[Long]           = None,
    currency:              Currency               = EUR,
    status:                FundraiseStatus        = INIT,
    score:                 Option[String]         = None,
    tag:                   Option[String]         = None,
    private_group_id:      Option[String]         = None,
    is_featured:           Option[Boolean]        = None,
    form_id:               Option[String]         = None,
    offer:                 LoanOffer              = LoanOffer(),
    custom:                Option[JsObject]       = None
)

object FundraiseLoan {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit lazy val loan_offer_format = LoanOffer.format
  val format = Jsonx.formatCaseClass[FundraiseLoan]
}
