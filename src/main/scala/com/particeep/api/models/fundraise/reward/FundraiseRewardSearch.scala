package com.particeep.api.models.fundraise.reward

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

/**
 * Created by Noe on 27/01/2017.
 */
case class FundraiseRewardSearch(
    start_date:     Option[OffsetDateTime] = None,
    end_date:       Option[OffsetDateTime] = None,
    status:         Option[String]         = None,
    enterprise_ids: Option[String]         = None
)

object FundraiseRewardSearch {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[FundraiseRewardSearch]
}
