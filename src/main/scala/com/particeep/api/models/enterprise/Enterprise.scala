package com.particeep.api.models.enterprise

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import play.api.libs.json.{ Format, JsObject, Json, Writes }

case class Enterprise(
    id:                           String                 = "",
    created_at:                   Option[OffsetDateTime] = None,
    creation_date:                Option[OffsetDateTime] = None,
    name:                         String                 = "",
    activity_domain:              Option[String]         = None,
    description_short:            Option[String]         = None,
    description_long:             Option[String]         = None,
    share_price:                  Option[Int]            = None,
    logo_url:                     Option[String]         = None,
    image_cover_url:              Option[String]         = None,
    video_url:                    Option[String]         = None,
    website_url:                  Option[String]         = None,
    is_auto_assigned_to_partners: Option[Boolean]        = None,
    status:                       Option[String]         = None,
    tag:                          Option[String]         = None,
    custom:                       Option[JsObject]       = None,
    address:                      Option[Address]        = None,
    creator_type:                 Option[String]         = None,
    creator_name:                 Option[String]         = None
)

object Enterprise {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address] = Address.format
  val format = Json.format[Enterprise]
}
