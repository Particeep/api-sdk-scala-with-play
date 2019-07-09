package com.particeep.api.models.document

import java.time.ZonedDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.Json

case class TransactionDocument(
  id:             String                = "",
  created_at:     Option[ZonedDateTime] = None,
  created_by:     String                = "",
  user_id:        String                = "",
  transaction_id: String                = "",
  document_id:    String                = "",
  signature_id:   Option[String]        = None,
  deleted_at:     Option[ZonedDateTime] = None
)

object TransactionDocument {
  implicit val date_format = Formatter.ZonedDateTimeWrites
  val format = Json.format[TransactionDocument]
}
