package com.particeep.api.models.club_deal

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, Writes }

/**
 * Created by Noe on 04/07/2017.
 */
case class DealGroupMember(
    id:            String                 = "",
    created_at:    Option[OffsetDateTime] = None,
    deal_group_id: String                 = "",
    user_id:       Option[String]         = None,
    email:         String                 = "",
    tag:           Option[String]         = None
)

object DealGroupMember {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[DealGroupMember]
}
