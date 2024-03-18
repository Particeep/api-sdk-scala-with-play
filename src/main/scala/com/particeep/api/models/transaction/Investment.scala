package com.particeep.api.models.transaction

import com.particeep.api.models.user.User
import play.api.libs.json.{ Json, OFormat }

case class Investment(
    user:        Option[User],
    transaction: Transaction
)

object Investment {
  implicit val user_format: OFormat[User] = User.format
  implicit val transaction_format: OFormat[Transaction] = Transaction.format
  val format = Json.format[Investment]
}
