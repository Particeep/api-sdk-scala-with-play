package com.particeep.api.models.user.relative

import play.api.libs.functional.syntax._
import play.api.libs.json._

import com.particeep.api.models.user.User

case class NewRelative(
  user: User,
  role: RelativeRole
)

object NewRelative {
  private[this] val user_writes: OWrites[User]       = User.format
  private[this] implicit val user_reads: Reads[User] = User.format

  private[this] implicit val reads: Reads[NewRelative] = Json.reads[NewRelative]

  private[this] implicit val writes: Writes[NewRelative] = (
    user_writes and
      (__ \ "role").write[RelativeRole]
  ) { new_relative =>
    (
      new_relative.user,
      new_relative.role
    )
  }

  val format: Format[NewRelative] = Format(reads, writes)
}
