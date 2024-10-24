package com.particeep.api.models.document

import play.api.http.HeaderNames

import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString

case class DocumentDownload(
  body:    Source[ByteString, _],
  headers: Map[String, scala.collection.Seq[String]]
) {
  lazy val content_type: String =
    headers
      .get(HeaderNames.CONTENT_TYPE)
      .flatMap(_.headOption)
      .getOrElse("application/octet-stream")

  lazy val content_length: Option[Long]       =
    headers
      .get(HeaderNames.CONTENT_LENGTH) match {
      case Some(Seq(length)) => length.toLongOption
      case _                 => None
    }
  def getHeader(name: String): Option[String] = headers.get(name).flatMap(_.headOption)
}
