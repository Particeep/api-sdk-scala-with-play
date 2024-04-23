package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.transaction.Transaction
import com.particeep.api.models.user.User

case class Lend(
  transaction: Transaction,
  user:        Option[User]
)

object Lend {
  implicit val transaction_format: OFormat[Transaction] = Transaction.format
  implicit val user_format: OFormat[User]               = User.format
  val format: OFormat[Lend]                             = Json.format[Lend]
}
