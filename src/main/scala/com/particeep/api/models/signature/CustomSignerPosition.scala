package com.particeep.api.models.signature

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

import com.particeep.api.models.enums.NewSignerType

final case class CustomSignerPosition(
  signer_type: NewSignerType,
  configs:     List[CustomPosition]
)

object CustomSignerPosition {
  implicit val CustomSignerPositionFormat: OFormat[CustomSignerPosition] = Jsonx.formatCaseClass[CustomSignerPosition]
}

final case class CustomPosition(
  page:  Int,
  x:     Int,
  y:     Int,
  width: Int = 180 // Only for yousign for now.
)
object CustomPosition       {
  implicit val CustomPositionFormat: OFormat[CustomPosition] = Jsonx.formatCaseClass[CustomPosition]
}
