package com.particeep.api.models.fundraise.equity

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsObject, Json }

import java.time.ZonedDateTime

/**
 * Created by Noe on 16/05/2017.
 */
case class FundraiseEquityRunningEdition(
  name:                  Option[String]        = None,
  description_short:     Option[String]        = None,
  description_long:      Option[String]        = None,
  description_offline:   Option[String]        = None,
  description_financial: Option[String]        = None,
  disclaimer_risk:       Option[String]        = None,
  disclaimer_fees:       Option[String]        = None,
  disclaimer_payment:    Option[String]        = None,
  end_at:                Option[ZonedDateTime] = None,
  score:                 Option[String]        = None,
  is_featured:           Option[Boolean]       = None,
  form_id:               Option[String]        = None,
  tag:                   Option[String]        = None,
  required_pro:          Option[Boolean]       = None,
  custom:                Option[JsObject]      = None
)

object FundraiseEquityRunningEdition {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[FundraiseEquityRunningEdition]
}
