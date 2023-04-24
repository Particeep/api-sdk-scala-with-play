package com.particeep.api.models.scoring_metrics

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class ScoringEvaluationSearch(
    created_after:  Option[OffsetDateTime] = None,
    created_before: Option[OffsetDateTime] = None,
    score:          Option[Long]           = None,
    metric_id:      Option[String]         = None,
    target_id:      Option[String]         = None,
    target_type:    Option[String]         = None,
    tag:            Option[String]         = None
)

object ScoringEvaluationSearch {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[ScoringEvaluationSearch]
}
