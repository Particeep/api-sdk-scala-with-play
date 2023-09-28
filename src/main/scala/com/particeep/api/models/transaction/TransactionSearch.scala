package com.particeep.api.models.transaction

import java.time.OffsetDateTime

case class TransactionSearch(
    issuer_id:        Option[String]  = None,
    issuer_type:      Option[String]  = None,
    issuer_name:      Option[String]  = None,
    recipient_id:     Option[String]  = None,
    recipient_type:   Option[String]  = None,
    recipient_name:   Option[String]  = None,
    fundraise_id:     Option[String]  = None,
    fundraise_type:   Option[String]  = None,
    item_id:          Option[String]  = None,
    item_type:        Option[String]  = None,
    amount:           Option[Int]     = None,
    fees:             Option[Int]     = None,
    partner_fees:     Option[Int]     = None,
    currency:         Option[String]  = None,
    status:           Option[String]  = None,
    control_status:   Option[String]  = None,
    payment_method:   Option[String]  = None,
    handled_offline:  Option[Boolean] = None,
    comment:          Option[String]  = None,
    targeting_roles:  Option[String]  = None,
    signature_status: Option[String]  = None
)

case class TransactionSearchAdditional(
    created_after:                         Option[OffsetDateTime] = None,
    created_before:                        Option[OffsetDateTime] = None,
    ids:                                   Option[String]         = None,
    tag:                                   Option[String]         = None,
    partner_manager_id:                    Option[String]         = None,
    partner_manager_company_business_name: Option[String]         = None
)
