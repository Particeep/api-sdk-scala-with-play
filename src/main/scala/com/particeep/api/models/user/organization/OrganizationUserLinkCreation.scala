package com.particeep.api.models.user.organization

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

import com.particeep.api.models.enums.OrganizationRole

case class OrganizationUserLinkCreation(
  role: OrganizationRole
)

object OrganizationUserLinkCreation {
  val format: OFormat[OrganizationUserLinkCreation] = Jsonx.formatCaseClass[OrganizationUserLinkCreation]
}
