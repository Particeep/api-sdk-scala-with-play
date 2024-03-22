package com.particeep.api.models.enterprise

import java.time.OffsetDateTime

case class EnterpriseSearch(
    name:                         Option[String]         = None,
    activity_domains:             Option[String]         = None,
    description_short:            Option[String]         = None,
    description_long:             Option[String]         = None,
    city:                         Option[String]         = None,
    ids:                          Option[String]         = None,
    createdAfter:                 Option[OffsetDateTime] = None,
    creationAfter:                Option[OffsetDateTime] = None,
    statuses:                     Option[String]         = None,
    tag:                          Option[String]         = None,
    is_auto_assigned_to_partners: Option[Boolean]        = None
)
