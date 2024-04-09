package com.particeep.api.models.form.creation

import play.api.libs.json.{ Json, OFormat }

case class AnswerCreationWithTag(
  tag:     String,
  answers: Seq[AnswerCreation]
)

object AnswerCreationWithTag {
  implicit lazy val answer_creation_format: OFormat[AnswerCreation] = AnswerCreation.format
  val format                                                        = Json.format[AnswerCreationWithTag]
}
