package com.particeep.api.models.form.get

import java.time.OffsetDateTime
import com.particeep.api.core.Formatter
import play.api.libs.json.{ Json, Writes }

case class Possibility(
    id:          String                      = "",
    created_at:  Option[OffsetDateTime]      = None,
    question_id: String                      = "",
    label:       Option[Map[String, String]] = None,
    index:       Option[Int]                 = None,
    weight:      Option[Int]                 = None
)

object Possibility {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  val format = Json.format[Possibility]
}
