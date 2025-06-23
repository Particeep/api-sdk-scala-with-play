package com.particeep.api.models.unicia

import play.api.libs.json.{ Json, OFormat }

case class ClientDataUnicia(
  user_id:   String,
  client_id: String,
  wallets:   Seq[ClientWalletUnicia],
  incomes:   Seq[ClientIncomeUnicia],
  taxes:     Seq[ClientTaxUnicia]
)

object ClientDataUnicia {
  implicit val format: OFormat[ClientDataUnicia] = Json.format[ClientDataUnicia]
}
