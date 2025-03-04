package com.particeep.api.models.signature

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

case class SignaturePositionOption(
                                    document_id: String,
                                    positions:   List[CustomSignerPosition],
                                  )

object SignaturePositionOption {
  implicit val SignaturePositionOptionFormat: OFormat[SignaturePositionOption] = Jsonx.formatCaseClass[SignaturePositionOption]
}
