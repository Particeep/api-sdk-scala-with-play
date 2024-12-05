package com.particeep.api.models.user.relative

import play.api.libs.functional.syntax._
import play.api.libs.json._

import com.particeep.api.models.user.UserCreation

case class NewRelativeCreation(
  user: UserCreation,
  role: RelativeRole
)

object NewRelativeCreation {
  private[this] val user_creation_writes: OWrites[UserCreation]       = UserCreation.format
  private[this] implicit val user_creation_reads: Reads[UserCreation] = UserCreation.format

  private[this] implicit val reads: Reads[NewRelativeCreation] = Json.reads[NewRelativeCreation]

  private[this] implicit val writes: Writes[NewRelativeCreation] = (
    user_creation_writes and
      (__ \ "role").write[RelativeRole]
  ) { new_relative_creation =>
    (
      new_relative_creation.user,
      new_relative_creation.role
    )
  }

  val format: Format[NewRelativeCreation] = Format(reads, writes)
}
