package com.particeep.api.models.scoring_metrics

import java.time.OffsetDateTime
case class ScoringMetricSearch(
    created_after:  Option[OffsetDateTime] = None,
    created_before: Option[OffsetDateTime] = None,
    formula_name:   Option[String]         = None,
    tag:            Option[String]         = None
)