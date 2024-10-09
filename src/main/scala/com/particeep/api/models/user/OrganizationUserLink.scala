package com.particeep.api.models.user

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.{ OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.enums.OrganizationRole

case class OrganizationUserLink(
  id:              String,
  created_at:      Option[OffsetDateTime] = None,
  created_by:      Option[String]         = None,
  user_id:         String,
  organization_id: String,
  role:            OrganizationRole
)

object OrganizationUserLink {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[OrganizationUserLink]        = Jsonx.formatCaseClass[OrganizationUserLink]
}
