package com.particeep.api.models.partner

import play.api.libs.json._

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class PartnerFees(
  id:                    String                 = "",
  created_at:            Option[OffsetDateTime] = None,
  deleted_at:            Option[OffsetDateTime] = None,
  created_by:            Option[String]         = None,
  user_id:               String                 = "",
  flat_fees:             Option[Int]            = None,
  variable_fees:         Option[Double]         = None,
  running_flat_fees:     Option[Int]            = None,
  running_variable_fees: Option[Double]         = None,
  frequency:             Option[Int]            = None,
  start_at:              Option[OffsetDateTime] = None,
  duration:              Option[Int]            = None,
  tag:                   Option[String]         = None,
  custom:                Option[JsObject]       = None
)

object PartnerFees {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format                                       = Json.format[PartnerFees]
}
