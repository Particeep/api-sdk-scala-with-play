package com.particeep.api.core

import play.api.libs.json.Writes

import java.time.format.DateTimeFormatter
import java.time.{ OffsetDateTime, ZoneOffset }

object Formatter {
  implicit val OffsetDateTimeWrites: Writes[OffsetDateTime] =
    Writes.temporalWrites[OffsetDateTime, DateTimeFormatter](DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC))
}
