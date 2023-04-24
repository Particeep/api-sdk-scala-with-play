package com.particeep.api.models.partner

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsObject, Json }

case class PartnerFeesOnTargetCreation(
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

object PartnerFeesOnTargetCreation {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[PartnerFeesOnTargetCreation]
}
