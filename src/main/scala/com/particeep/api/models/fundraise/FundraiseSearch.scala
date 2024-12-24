package com.particeep.api.models.fundraise

import ai.x.play.json.Encoders._
import ai.x.play.json.Jsonx
import play.api.libs.json.{ OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class FundraiseSearch(
  created_before:            Option[OffsetDateTime] = None,
  created_after:             Option[OffsetDateTime] = None,
  end_before:                Option[OffsetDateTime] = None,
  end_after:                 Option[OffsetDateTime] = None,
  id:                        Option[String]         = None,
  enterprise_id:             Option[String]         = None,
  manager_id:                Option[String]         = None,
  manager_email:             Option[String]         = None,
  fundraise_id:              Option[String]         = None,
  fundraise_type:            Option[String]         = None,
  name:                      Option[String]         = None,
  category:                  Option[String]         = None,
  city:                      Option[String]         = None,
  statuses:                  Option[String]         = None,
  amount_target_min:         Option[Long]           = None,
  amount_target_max:         Option[Long]           = None,
  amount_engaged_min:        Option[Long]           = None,
  amount_engaged_max:        Option[Long]           = None,
  percentage_completion_min: Option[Int]            = None,
  percentage_completion_max: Option[Int]            = None,
  score:                     Option[String]         = None,
  private_group_id:          Option[String]         = None,
  targeting_roles:           Option[String]         = None,
  tag:                       Option[String]         = None,
  fundraise_tag:             Option[String]         = None,
  required_pro:              Option[Boolean]        = None,
  is_featured:               Option[Boolean]        = None,
  creator_type:              Option[String]         = None,
  creator_name:              Option[String]         = None
)

object FundraiseSearch {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[FundraiseSearch]             = Jsonx.formatCaseClass[FundraiseSearch]
}
