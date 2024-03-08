package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{JsObject, Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

/**
 * Created by Noe on 16/05/2017.
 */
case class FundraiseLoanRunningEdition(
    name:                  Option[String]         = None,
    description_short:     Option[String]         = None,
    description_long:      Option[String]         = None,
    description_offline:   Option[String]         = None,
    description_financial: Option[String]         = None,
    disclaimer_risk:       Option[String]         = None,
    disclaimer_fees:       Option[String]         = None,
    disclaimer_payment:    Option[String]         = None,
    end_at:                Option[OffsetDateTime] = None,
    score:                 Option[String]         = None,
    form_id:               Option[String]         = None,
    tag:                   Option[String]         = None,
    is_featured:           Option[Boolean]        = None,
    custom:                Option[JsObject]       = None
)

object FundraiseLoanRunningEdition {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[FundraiseLoanRunningEdition]
}
