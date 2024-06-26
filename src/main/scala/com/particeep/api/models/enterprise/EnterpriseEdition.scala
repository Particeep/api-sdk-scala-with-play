package com.particeep.api.models.enterprise

import play.api.libs.json.{ Format, JsObject, Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.Address

case class EnterpriseEdition(
  user_id:                      Option[String]         = None,
  name:                         Option[String]         = None,
  creation_date:                Option[OffsetDateTime] = None,
  activity_domain:              Option[String]         = None,
  description_short:            Option[String]         = None,
  description_long:             Option[String]         = None,
  share_price:                  Option[Int]            = None,
  logo_url:                     Option[String]         = None,
  image_cover_url:              Option[String]         = None,
  video_url:                    Option[String]         = None,
  is_auto_assigned_to_partners: Option[Boolean]        = None,
  status:                       Option[String]         = None,
  tag:                          Option[String]         = None,
  custom:                       Option[JsObject]       = None,
  website_url:                  Option[String]         = None,
  address:                      Option[Address]        = None
)

object EnterpriseEdition {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address]     = Address.format
  val format: OFormat[EnterpriseEdition]           = Json.format[EnterpriseEdition]
}
