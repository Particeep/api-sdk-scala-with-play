package com.particeep.api.models.kyc_ecsp

import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders.encoder
import com.particeep.api.models.enums.KycEcspType
import play.api.libs.Files.logger
import play.api.libs.json.{ Format, JsNull, JsResult, JsValue, OFormat, Reads, Writes }

object KycEcspParser {
  implicit val kyc_ecsp_natural_format: OFormat[KycEcsp.Natural] = Jsonx.formatCaseClassUseDefaults[KycEcsp.Natural]
  implicit val kyc_ecsp_legal_format: OFormat[KycEcsp.Legal] = Jsonx.formatCaseClassUseDefaults[KycEcsp.Legal]

  private[this] def doFormat(kyc_type: KycEcspType): Format[KycEcsp] = {
    val writes = new Writes[KycEcsp] {
      def writes(o: KycEcsp): JsValue = (kyc_type, o) match {
        case (KycEcspType.LEGAL, l: KycEcsp.Legal)     => kyc_ecsp_legal_format.writes(l)
        case (KycEcspType.NATURAL, n: KycEcsp.Natural) => kyc_ecsp_natural_format.writes(n)
        case _                                         => logger.error("Unexpected error, type of kyc not matching"); JsNull
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

  private[this] val kyc_fmt_legal = doFormat(KycEcspType.LEGAL)
  private[this] val kyc_fmt_natural = doFormat(KycEcspType.NATURAL)

  val format = (kyc_type: KycEcspType) =>
    kyc_type match {
      case KycEcspType.LEGAL   => kyc_fmt_legal
      case KycEcspType.NATURAL => kyc_fmt_natural
    }
}