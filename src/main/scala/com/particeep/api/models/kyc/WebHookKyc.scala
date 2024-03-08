package com.particeep.api.models.kyc

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.KycType.{ ID_CARD, KycType }

case class WebHookKyc(
  owner_id:        String                 = "",
  doc_type:        KycType                = ID_CARD,
  created_at:      Option[OffsetDateTime] = None,
  update_at:       Option[OffsetDateTime] = None,
  refusal_reason:  Option[String]         = None,
  refusal_message: Option[String]         = None,
  urls:            Seq[String]            = Seq()
)

object WebHookKyc {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[WebHookKyc]
}
