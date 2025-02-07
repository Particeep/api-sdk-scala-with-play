package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString

import com.particeep.api.core._
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.document.Document
import com.particeep.api.models.document_generation.{ DocumentGeneration, DocumentGenerationAndUpload }

trait DocumentGenerationCapability {
  self: WSClient =>

  val document_generation: DocumentGenerationClient = new DocumentGenerationClient(this)

  def document_generation(credentials: ApiCredential): DocumentGenerationClient =
    new DocumentGenerationClient(this, Some(credentials))
}

object DocumentGenerationClient {
  private val endPoint: String                                                            = "/document-generation"
  private implicit val format_generation_and_upload: OFormat[DocumentGenerationAndUpload] =
    DocumentGenerationAndUpload.format
  private implicit val format_generation: OFormat[DocumentGeneration]                     = DocumentGeneration.format
  private implicit val format_document: OFormat[Document]                                 = Document.format
}

class DocumentGenerationClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import DocumentGenerationClient._

  def generationAndUpload(
    document_generation: DocumentGenerationAndUpload,
    owner_id:            String,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, Document]] = {
    ws.post[Document](s"$endPoint/upload/$owner_id", timeout, Json.toJson(document_generation))
  }

  def generation(
    document_generation: DocumentGeneration,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    ws.postStream(endPoint, timeout, Json.toJson(document_generation))
  }
}
