package com.particeep.api.models.kyc

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.KycStatus.{ CREATED, KycStatus }
import com.particeep.api.models.enums.KycType.{ ID_CARD, KycType }
import play.api.libs.json.{ Json, Writes }

case class Kyc(
    doc_type:        KycType                = ID_CARD,
    created_at:      Option[OffsetDateTime] = None,
    update_at:       Option[OffsetDateTime] = None,
    refusal_reason:  Option[String]         = None,
    refusal_message: Option[String]         = None,
    status:          KycStatus              = CREATED,
    urls:            Seq[String]            = Seq()
)

object Kyc {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[Kyc]
}
