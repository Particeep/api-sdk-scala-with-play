package com.particeep.api.models.wallet.sepa

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class SddCashIn(
  amount:         Int                    = 0,
  fees:           Option[Int]            = None,
  collectionDate: Option[OffsetDateTime] = None,
  owner_ip:       String                 = "",
  tag:            Option[String]         = None
)

object SddCashIn {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[SddCashIn]                   = Json.format[SddCashIn]
}
