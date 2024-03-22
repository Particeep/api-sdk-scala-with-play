package com.particeep.api.models.enterprise

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import play.api.libs.json.{ Format, JsObject, Json, Writes }

case class EnterpriseCreation(
    user_id:           String                 = "",
    name:              String,
    creation_date:     Option[OffsetDateTime] = None,
    activity_domain:   Option[String]         = None,
    description_short: Option[String]         = None,
    description_long:  Option[String]         = None,
    logo_url:          Option[String]         = None,
    image_cover_url:   Option[String]         = None,
    video_url:         Option[String]         = None,
    status:            Option[String]         = None,
    tag:               Option[String]         = None,
    custom:            Option[JsObject]       = None,
    address:           Option[Address]        = None,
    creator_type:      Option[String]         = None,
    creator_name:      Option[String]         = None
)

object EnterpriseCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address] = Address.format
  val format = Json.format[EnterpriseCreation]
}
