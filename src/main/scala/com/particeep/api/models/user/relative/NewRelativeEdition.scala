package com.particeep.api.models.user.relative

import play.api.libs.functional.syntax._
import play.api.libs.json._

import com.particeep.api.models.user.UserEdition

case class NewRelativeEdition(
  user: UserEdition,
  role: Option[RelativeRole] = None
)

object NewRelativeEdition {
  private[this] val user_edition_writes: OWrites[UserEdition]       = UserEdition.format
  private[this] implicit val user_edition_reads: Reads[UserEdition] = UserEdition.format

  implicit val reads: Reads[NewRelativeEdition] = Json.reads[NewRelativeEdition]

  implicit val writes: Writes[NewRelativeEdition] = (
    user_edition_writes and
      (__ \ "role").write[Option[RelativeRole]]
  ) { new_relative_edition =>
    (
      new_relative_edition.user,
      new_relative_edition.role
    )
  }

  val format: Format[NewRelativeEdition] = Format(reads, writes)
}
