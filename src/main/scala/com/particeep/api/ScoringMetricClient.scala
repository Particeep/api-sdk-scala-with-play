package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.scoring_metrics._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait ScoringMetricsCapability {
  self: WSClient =>

  val scoring_metric: ScoringMetricClient = new ScoringMetricClient(this)

  def scoring_metric(credentials: ApiCredential): ScoringMetricClient = new ScoringMetricClient(this, Some(credentials))
}

object ScoringMetricClient {
  private val endPoint: String                                                                = "/scoring-metrics"
  private implicit val scoring_evaluation_format: OFormat[ScoringEvaluation]                  = ScoringEvaluation.format
  private implicit val scoring_evaluation_creation_format: OFormat[ScoringEvaluationCreation] =
    ScoringEvaluationCreation.format
}

class ScoringMetricClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import ScoringMetricClient._

  def runEvaluation(
    metric_id:     String,
    se_creation:   ScoringEvaluationCreation,
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, ScoringEvaluation]] = {
    ws.post[ScoringEvaluation](s"$endPoint/$metric_id/evals", timeout, Json.toJson(se_creation))
  }

  def searchEvaluations(
    criteria:       ScoringEvaluationSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[ScoringEvaluation]]] = {
    ws.get[PaginatedSequence[ScoringEvaluation]](
      s"$endPoint/evals/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }
}
