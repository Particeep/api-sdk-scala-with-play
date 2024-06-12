package com.particeep.api.models.signature

import java.time.OffsetDateTime

case class SignatureSearch(
  created_after:  Option[OffsetDateTime] = None,
  created_before: Option[OffsetDateTime] = None,
  language:       Option[String]         = None,
  doc_id:         Option[String]         = None,
  fileToSignUrl:  Option[String]         = None,
  signed_doc_id:  Option[String]         = None,
  fileName:       Option[String]         = None,
  status:         Option[String]         = None,
  external_id:    Option[String]         = None,
  firstName:      Option[String]         = None,
  lastName:       Option[String]         = None,
  useName:        Option[String]         = None,
  email:          Option[String]         = None,
  phone:          Option[String]         = None,
  description:    Option[String]         = None,
  signer_order:   Option[Int]            = None,
  total_signer:   Option[Int]            = None,
  ids:            Option[String]         = None
)

case class SignatureSearchForEntities(
  target_id:                Option[String] = None,
  target_type:              Option[String] = None,
  group_id:                 Option[String] = None,
  partner_ids:              Option[String] = None,
  fundraise_id:             Option[String] = None,
  fundraise_type:           Option[String] = None,
  fundraise_name:           Option[String] = None,
  signature_type:           Option[String] = None,
  signer_type:              Option[String] = None,
  document_id_for_position: Option[String] = None
)
