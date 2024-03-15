package com.particeep.api.models.wallet.sepa

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class SddCashIn(
    amount:         Int                    = 0,
    fees:           Option[Int]            = None,
    collectionDate: Option[OffsetDateTime] = None,
    owner_ip:       String                 = "",
    tag:            Option[String]         = None
)

object SddCashIn {
  implicit val date_format: play.api.libs.json.Writes[java.time.OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[SddCashIn]
}