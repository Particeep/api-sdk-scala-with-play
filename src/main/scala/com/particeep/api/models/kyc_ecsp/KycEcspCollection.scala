package com.particeep.api.models.kyc_ecsp

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

case class KycEcspCollection(
    kycs_legal:   Seq[KycEcsp.Legal]   = Seq(),
    kycs_natural: Seq[KycEcsp.Natural] = Seq()
)

object KycEcspCollection {
  implicit val format: OFormat[KycEcspCollection] = Jsonx.formatCaseClassUseDefaults[KycEcspCollection]
}
