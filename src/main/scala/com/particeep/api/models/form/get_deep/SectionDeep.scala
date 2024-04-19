package com.particeep.api.models.form.get_deep

import play.api.libs.json.{ Json, OFormat, Writes }

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class SectionDeep(
  id:          String                      = "",
  created_at:  Option[OffsetDateTime]      = None,
  form_id:     String                      = "",
  name:        Option[Map[String, String]] = None,
  description: Option[Map[String, String]] = None,
  index:       Option[Int]                 = None,
  questions:   Seq[QuestionDeep]           = Seq(),

  // Only when getting a Form for a User
  done: Option[Boolean] = None
)

object SectionDeep {
  implicit val date_format: Writes[OffsetDateTime]    = Formatter.OffsetDateTimeWrites
  implicit val question_format: OFormat[QuestionDeep] = QuestionDeep.format
  val format: OFormat[SectionDeep] = Json.format[SectionDeep]
}
