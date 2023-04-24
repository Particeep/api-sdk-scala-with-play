package com.particeep.api.core

import java.time.{ ZoneOffset, OffsetDateTime }
import java.time.format.DateTimeFormatter

import play.api.libs.json.Writes

object Formatter {
  implicit val OffsetDateTimeWrites = Writes.temporalWrites[OffsetDateTime, DateTimeFormatter](DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC))
}
