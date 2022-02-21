package com.particeep.api.models.user

import play.api.libs.json.{ JsObject, Json }

case class RelativeLegalCreation(
    company_name:              Option[String]   = None,
    siret:                     Option[String]   = None,
    legal_status:              Option[String]   = None,
    representative_email:      Option[String]   = None,
    representative_first_name: Option[String]   = None,
    representative_last_name:  Option[String]   = None,
    representative_phone:      Option[String]   = None,
    representative_function:   Option[String]   = None,
    tag:                       Option[String]   = None,
    custom:                    Option[JsObject] = None
)

object RelativeLegalCreation {
  val format = Json.format[RelativeLegalCreation]
}
