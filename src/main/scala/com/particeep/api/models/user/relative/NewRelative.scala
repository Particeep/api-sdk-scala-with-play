package com.particeep.api.models.user.relative

import play.api.libs.functional.syntax._
import play.api.libs.json._

import com.particeep.api.models.user.User

case class NewRelative(
  user: User,
  role: RelativeRole
)

object NewRelative {
  private[this] implicit val user_writes: OWrites[User] = User.format
  private[this] val user_reads: Reads[User]             = User.format

  private[this] implicit val reads: Reads[NewRelative] = (
    user_reads and
      (__ \ "role").read[RelativeRole]
  ) { (user, role) =>
    NewRelative(
      user = user,
      role = role
    )
  }

  private[this] implicit val writes: Writes[NewRelative] = Json.writes[NewRelative]

  val format: Format[NewRelative] = Format(reads, writes)
}
