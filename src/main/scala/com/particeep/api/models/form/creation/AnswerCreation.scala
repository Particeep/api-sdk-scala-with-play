package com.particeep.api.models.form.creation

import play.api.libs.json._

case class AnswerCreation(
  question_id: String,
  answer:      Seq[String]
)

object AnswerCreation {
  val format = Json.format[AnswerCreation]
}
