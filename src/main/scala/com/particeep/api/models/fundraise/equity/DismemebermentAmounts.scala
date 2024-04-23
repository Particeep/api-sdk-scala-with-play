package com.particeep.api.models.fundraise.equity

import play.api.libs.json.{ Json, OFormat }

case class DismemebermentAmounts(
  bare_owner_amount:   Int,
  usufructuary_amount: Int
)

object DismemebermentAmounts {
  val format: OFormat[DismemebermentAmounts] = Json.format[DismemebermentAmounts]
}
