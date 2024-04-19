package com.particeep.api.models.form.creation

import play.api.libs.json.{Json, OFormat}

case class AnswerCreation(
  question_id: String,
  answer:      Seq[String]
)

object AnswerCreation {
  val format: OFormat[AnswerCreation] = Json.format[AnswerCreation]
}
