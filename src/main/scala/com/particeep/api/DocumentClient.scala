package com.particeep.api

import play.api.libs.json.{ Json, OFormat }
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.document._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait DocumentCapability {
  self: WSClient =>

  def document(credentials: ApiCredential): DocumentClient = new DocumentClient(this, credentials)
}

object DocumentClient {
  private val endPoint: String                                    = "/document"
  private implicit val format: OFormat[Document]                  = Document.format
  private implicit val format_creation: OFormat[DocumentCreation] = DocumentCreation.format
  private implicit val format_edition: OFormat[DocumentEdition]   = DocumentEdition.format
}

class DocumentClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import DocumentClient._

  implicit val creds: ApiCredential = credentials

  def upload(
    owner_id:          String,
    file:              File,
    content_type:      String,
    document_creation: DocumentCreation,
    timeout:           Long = defaultTimeOut
  )(implicit exec:     ExecutionContext): Future[Either[ErrorResult, Document]] = {
    val bodyParts = List(
      new StringPart("target_id", document_creation.target_id.getOrElse("")),
      new StringPart("target_type", document_creation.target_type.getOrElse("")),
      new StringPart("description", document_creation.description.getOrElse("")),
      new StringPart("name", document_creation.name.getOrElse("")),
      new StringPart("path", document_creation.path.getOrElse("")),
      new StringPart("tag", document_creation.tag.getOrElse("")),
      new StringPart("locked", document_creation.locked.getOrElse(false).toString),
      new StringPart("override_existing_file", document_creation.override_existing_file.getOrElse(false).toString),
      new StringPart("scope", document_creation.scope.getOrElse(Scope.PRIVATE).name)
    )
    ws.postFile[Document](s"$endPoint/$owner_id/upload", timeout, file, content_type, bodyParts)
  }

  def createDir(owner_id: String, document_creation: DocumentCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                 ExecutionContext
  ): Future[Either[ErrorResult, Document]] = {
    ws.post[Document](s"$endPoint/$owner_id/dir", timeout, Json.toJson(document_creation))
  }

  def download(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:          ExecutionContext
  ): Future[Either[ErrorResult, DocumentDownload]] = {
    ws.getDoc(id, timeOut = timeout)
  }

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, Document]] = {
    ws.get[Document](s"$endPoint/$id", timeout)
  }

  def byIds(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[Document]]] = {
    ws.get[List[Document]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def update(id: String, document_edition: DocumentEdition, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Document]] = {
    ws.post[Document](s"$endPoint/$id", timeout, Json.toJson(document_edition))
  }

  def search(criteria: DocumentSearch, table_criteria: TableSearch, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, PaginatedSequence[Document]]] = {
    ws.get[PaginatedSequence[Document]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Document]] = {
    ws.delete[Document](s"$endPoint/$id", timeout)
  }

  def deleteFiles(
    ids:           List[String],
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, List[Document]]] = {
    ws.delete[List[Document]](s"$endPoint/files", timeout, Json.obj("ids" -> ids))
  }

  def generateTimeBoundedUrls(
    documents_ids: List[String],
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, TimeBoundedUrls]] = {
    ws.generateTimeBoundedUrls(
      path         = s"$endPoint/generate-time-bounded-urls",
      timeOut      = timeout,
      documentsIds = documents_ids
    )
  }

  def zipByIds(seq_id: Seq[String], timeout: Long = defaultTimeOut)(implicit
    ec:                ExecutionContext
  ): Future[Either[ErrorResult, DocumentDownload]] = {
    ws.getZip(seq_id, timeout)
  }
}
