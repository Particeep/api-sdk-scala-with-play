package com.particeep.api.models.fundraise.equity

import play.api.libs.json.{Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class FundraiseEquitySearch(
    start_date:     Option[OffsetDateTime] = None,
    end_date:       Option[OffsetDateTime] = None,
    status:         Option[String]         = None,
    enterprise_ids: Option[String]         = None
)

object FundraiseEquitySearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[FundraiseEquitySearch]
}
