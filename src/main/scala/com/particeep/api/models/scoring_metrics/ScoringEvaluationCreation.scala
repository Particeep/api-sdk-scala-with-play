package com.particeep.api.models.scoring_metrics

import play.api.libs.json.{JsObject, Json, OFormat}

case class ScoringEvaluationCreation(
  input_json:  JsObject,
  target_id:   Option[String] = None,
  target_type: Option[String] = None
)

object ScoringEvaluationCreation {
  val format: OFormat[ScoringEvaluationCreation] = Json.format[ScoringEvaluationCreation]
}
