package com.particeep.api.models.form.get

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class Answer(
  id:          String                 = "",
  created_at:  Option[OffsetDateTime] = None,
  user_id:     String                 = "",
  question_id: String                 = "",
  label:       Option[String]         = None
)

object Answer {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format: OFormat[Answer]                      = Json.format[Answer]
}
