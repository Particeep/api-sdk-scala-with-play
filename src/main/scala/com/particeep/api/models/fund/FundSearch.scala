package com.particeep.api.models.fund

import java.time.ZonedDateTime

case class FundSearch(
    created_before:               Option[ZonedDateTime] = None,
    created_after:                Option[ZonedDateTime] = None,
    recipient_id:                 Option[String]        = None,
    recipient_type:               Option[String]        = None,
    recipient_email:              Option[String]        = None,
    name:                         Option[String]        = None,
    status:                       Option[String]        = None,
    amount_engaged_min:           Option[Long]          = None,
    amount_engaged_max:           Option[Long]          = None,
    tag:                          Option[String]        = None,
    required_pro:                 Option[Boolean]       = None,
    is_auto_assigned_to_partners: Option[Boolean]       = None,
    category:                     Option[String]        = None,
    targeting_roles:              Option[String]        = None
)
