package com.particeep.api.models.fundraise.reward

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsObject, Json }

/**
 * Created by Noe on 26/01/2017.
 */
case class FundraiseRewardRunningEdition(
    name:                Option[String]         = None,
    description_short:   Option[String]         = None,
    description_long:    Option[String]         = None,
    description_offline: Option[String]         = None,
    end_at:              Option[OffsetDateTime] = None,
    tag:                 Option[String]         = None,
    custom:              Option[JsObject]       = None
)
object FundraiseRewardRunningEdition {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[FundraiseRewardRunningEdition]
}

