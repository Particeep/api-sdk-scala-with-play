package com.particeep.api.models.scoring_metrics

import play.api.libs.json.{ JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

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
  val format: OFormat[ScoringEvaluation]           = Json.format[ScoringEvaluation]
}
