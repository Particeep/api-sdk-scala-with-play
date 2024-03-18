package com.particeep.api.models.form.get

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, Writes }

case class Answer(
    id:          String                 = "",
    created_at:  Option[OffsetDateTime] = None,
    user_id:     String                 = "",
    question_id: String                 = "",
    label:       Option[String]         = None
)

object Answer {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[Answer]
}
