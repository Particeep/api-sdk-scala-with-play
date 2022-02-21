package com.particeep.api.models.user

import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import play.api.libs.json.JsObject

import java.time.ZonedDateTime

case class RelativeLegal(
    id:                        String                = "",
    created_at:                Option[ZonedDateTime] = None,
    created_by:                Option[String]        = None,
    deleted_at:                Option[ZonedDateTime] = None,
    user_id:                   String,
    company_name:              Option[String]        = None,
    siret:                     Option[String]        = None,
    legal_status:              Option[String]        = None,
    representative_email:      Option[String]        = None,
    representative_first_name: Option[String]        = None,
    representative_last_name:  Option[String]        = None,
    representative_phone:      Option[String]        = None,
    representative_function:   Option[String]        = None,
    tag:                       Option[String]        = None,
    custom:                    Option[JsObject]      = None
)

object RelativeLegal {
  val format = Jsonx.formatCaseClass[RelativeLegal]
}
