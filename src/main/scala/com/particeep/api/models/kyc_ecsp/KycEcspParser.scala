package com.particeep.api.models.kyc_ecsp

import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders.encoder
import com.particeep.api.models.enums.KycEcspType
import com.particeep.api.models.kyc_ecsp.KycEcsp.{ Legal, Natural }
import play.api.libs.json.{ Format, JsResult, JsValue, OFormat, Reads, Writes }

object KycEcspParser {
  implicit val kyc_ecsp_natural_format: OFormat[Natural] = Jsonx.formatCaseClassUseDefaults[KycEcsp.Natural]
  implicit val kyc_ecsp_legal_format: OFormat[Legal] = Jsonx.formatCaseClassUseDefaults[KycEcsp.Legal]

  def format(kyc_type: KycEcspType): Format[KycEcsp] = {
    val writes = new Writes[KycEcsp] {
      def writes(o: KycEcsp): JsValue = kyc_type match {
        case KycEcspType.LEGAL   => kyc_ecsp_legal_format.writes(o.asInstanceOf[KycEcsp.Legal])
        case KycEcspType.NATURAL => kyc_ecsp_natural_format.writes(o.asInstanceOf[KycEcsp.Natural])
      }
    }

    val reads = new Reads[KycEcsp] {
      def reads(o: JsValue): JsResult[KycEcsp] = kyc_type match {
        case KycEcspType.LEGAL   => kyc_ecsp_legal_format.reads(o)
        case KycEcspType.NATURAL => kyc_ecsp_natural_format.reads(o)
      }
    }

    Format(reads, writes)
  }
}
