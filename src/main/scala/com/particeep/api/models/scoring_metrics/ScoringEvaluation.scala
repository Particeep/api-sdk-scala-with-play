package com.particeep.api.models.scoring_metrics

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, JsObject, Writes }

case class ScoringEvaluation(
    id:            String                 = "",
    created_at:    Option[OffsetDateTime] = None,
    score:         Option[Long]           = None,
    risk:          Option[String]         = None,
    metric_id:     String,
    source_params: JsObject,
    target_id:     Option[String]         = None,
    target_type:   Option[String]         = None,
    tag:           Option[String]         = None,
    custom:        Option[JsObject]       = None
)

object ScoringEvaluation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[ScoringEvaluation]
}
