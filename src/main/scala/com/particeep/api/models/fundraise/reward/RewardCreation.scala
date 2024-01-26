package com.particeep.api.models.fundraise.reward

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

/**
 * Created by Noe on 26/01/2017.
 */
case class RewardCreation(
    name:         Option[String]         = None,
    price:        Option[Int]            = None,
    img_url:      Option[String]         = None,
    quantity:     Option[Int]            = None,
    delivered_at: Option[OffsetDateTime] = None,
    description:  Option[String]         = None,
    tag:          Option[String]         = None
)

object RewardCreation {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[RewardCreation]
}
