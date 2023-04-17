package com.particeep.api.models.scoring_metrics

import play.api.libs.json.{ JsObject, Json }
import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class ScoringMetric(
    id:             String                 = "",
    created_at:     Option[OffsetDateTime] = None,
    type_signature: Option[TypeSignature]  = None,
    formula_name:   Option[String]         = None,
    formula_code:   String,
    code_checksum:  String,
    tag:            Option[String]         = None,
    custom:         Option[JsObject]       = None
)

object ScoringMetric {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  implicit val type_signature_reads = TypeSignature.typeSignatureReads
  implicit val type_signature_writes = TypeSignature.typeSignatureWrites
  val format = Json.format[ScoringMetric]
}
