package com.particeep.api.models.wallet.sepa

import play.api.libs.json.{Json, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.SddMandateStatus.{ PENDING, SddMandateStatus }

case class SddMandate(
    id:         String                 = "",
    created_at: Option[OffsetDateTime] = None,
    holder:     String                 = "",
    bic:        String                 = "",
    iban:       String                 = "",
    is_b2b:     Boolean                = true,
    street:     String                 = "",
    zip:        String                 = "",
    city:       String                 = "",
    country:    String                 = "",
    wallet_id:  String                 = "",
    status:     SddMandateStatus       = PENDING,
    tag:        Option[String]         = None
)

object SddMandate {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[SddMandate]
}
