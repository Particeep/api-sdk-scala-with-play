package com.particeep.api.models.transaction

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.user.User

case class Investment(
  user:        Option[User],
  transaction: Transaction
)

object Investment {
  implicit val user_format: OFormat[User]               = User.format
  implicit val transaction_format: OFormat[Transaction] = Transaction.format
  val format: OFormat[Investment]                       = Json.format[Investment]
}
