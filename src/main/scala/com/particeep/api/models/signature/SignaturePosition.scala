package com.particeep.api.models.signature

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

import java.time.OffsetDateTime

final case class SignaturePosition(
  id:          String,
  created_at:  OffsetDateTime,
  document_id: String,
  positions:   List[CustomSignerPosition]
)

object SignaturePosition {
  implicit val SignaturePositionFormat: OFormat[SignaturePosition] = Jsonx.formatCaseClass[SignaturePosition]
}
