package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ Json, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class FundraiseLoanSearch(
  start_date:     Option[OffsetDateTime] = None,
  end_date:       Option[OffsetDateTime] = None,
  status:         Option[String]         = None,
  enterprise_ids: Option[String]         = None,
  term_min:       Option[Int]            = None,
  term_max:       Option[Int]            = None,
  rate_min:       Option[Double]         = None,
  rate_max:       Option[Double]         = None
)

object FundraiseLoanSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[FundraiseLoanSearch]
}
