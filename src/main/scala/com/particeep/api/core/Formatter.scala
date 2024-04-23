package com.particeep.api.core

import play.api.libs.json.{ Format, JsValue, Json, Writes }

import java.time.format.DateTimeFormatter
import java.time.{ OffsetDateTime, ZoneOffset }
import scala.util.matching.Regex

object Formatter {
  implicit val OffsetDateTimeWrites: Writes[OffsetDateTime] =
    Writes.temporalWrites[OffsetDateTime, DateTimeFormatter](DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC))

  implicit val regex_format: Format[Regex] = Format(
    (json: JsValue) => json.validate[String].map(new Regex(_)),
    (regex: Regex) => Json.toJson(regex.toString())
  )
}
