package com.particeep.api.models.partner

import com.particeep.api.models.enums.OriasStatus.OriasStatus
import com.particeep.api.models.enums.RegulatoryStatus.RegulatoryStatus
import play.api.libs.json.{ JsObject, Json }

case class PartnerCompanyCreation(
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

object PartnerCompanyCreation {
  val format = Json.format[PartnerCompanyCreation]
}
