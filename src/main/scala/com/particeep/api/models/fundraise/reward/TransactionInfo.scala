package com.particeep.api.models.fundraise.reward

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

/**
 * Created by Noe on 26/01/2017.
 */

case class TransactionInfo(
    amount:     Int                    = 0,
    comment:    Option[String]         = None,
    created_at: Option[OffsetDateTime]
)

object TransactionInfo {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[TransactionInfo]
}
