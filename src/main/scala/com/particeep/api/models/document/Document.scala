package com.particeep.api.models.document

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import play.api.libs.json.{ JsObject, Json }

/**
 * Created by Noe on 04/01/2017.
 */
case class Document(
    id:           String                 = "",
    created_at:   Option[OffsetDateTime] = None,
    owner_id:     String                 = "",
    target_id:    Option[String]         = None,
    target_type:  Option[String]         = None,
    name:         String                 = "",
    description:  Option[String]         = None,
    content_type: Option[String]         = None,
    path:         String                 = "",
    doc_type:     String                 = "",
    folder_id:    Option[String]         = None,
    locked:       Boolean                = false,
    permalink:    Option[String]         = None,
    scope:        String,
    tag:          Option[String]         = None,
    custom:       Option[JsObject]       = None
)

object Document {
  implicit val date_format = Formatter.OffsetDateTimeWrites
  val format = Json.format[Document]
}
