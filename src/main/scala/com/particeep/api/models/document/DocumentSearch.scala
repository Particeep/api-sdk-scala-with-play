package com.particeep.api.models.document

import play.api.data.Form
import play.api.data.Forms.{ mapping, optional, text }

/**
 * Created by Noe on 04/01/2017.
 */
case class DocumentSearch(
  owner_id:     Option[String] = None,
  target_id:    Option[String] = None,
  target_type:  Option[String] = None,
  path:         Option[String] = None,
  name:         Option[String] = None,
  description:  Option[String] = None,
  content_type: Option[String] = None,
  external_id:  Option[String] = None,
  doc_type:     Option[String] = None,
  tag:          Option[String] = None,
  custom:       Option[String] = None,
  ids:          Option[String] = None
)

object DocumentSearch {
  val document_search_form: Form[DocumentSearch] = Form(
    mapping(
      "owner_id"     -> optional(text),
      "target_id"    -> optional(text),
      "target_type"  -> optional(text),
      "path"         -> optional(text),
      "name"         -> optional(text),
      "description"  -> optional(text),
      "content_type" -> optional(text),
      "external_id"  -> optional(text),
      "doc_type"     -> optional(text),
      "tag"          -> optional(text),
      "custom"       -> optional(text),
      "ids"          -> optional(text)
    )(DocumentSearch.apply)(DocumentSearch.unapply)
  )
}
