package com.particeep.api.models.partner

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.OriasStatus.OriasStatus
import com.particeep.api.models.enums.RegulatoryStatus.RegulatoryStatus
import ai.x.play.json.Jsonx
import ai.x.play.json.Encoders._
import play.api.libs.json.JsObject

case class PartnerCompany(
    id:                   String,
    created_at:           OffsetDateTime,
    user_id:              String,
    is_rcs_verified:      Option[Boolean]          = None,
    regulatory_status:    Option[RegulatoryStatus] = None,
    orias_status:         Option[OriasStatus]      = None,
    orias_number:         Option[String]           = None,
    regafi_number:        Option[String]           = None,
    geco_number:          Option[String]           = None,
    legal_representative: Option[String]           = None,
    contact_first_name:   Option[String]           = None,
    contact_last_name:    Option[String]           = None,
    contact_email:        Option[String]           = None,
    unicia_id:            Option[String]           = None,
    tag:                  Option[String]           = None,
    custom:               Option[JsObject]         = None
)

object PartnerCompany {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Jsonx.formatCaseClass[PartnerCompany]
}
