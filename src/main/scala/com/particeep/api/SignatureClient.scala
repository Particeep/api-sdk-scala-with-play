package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString

import com.particeep.api.core._
import com.particeep.api.models.signature._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait SignatureCapability {
  self: WSClient =>

  val signature: SignatureClient                             = new SignatureClient(this)
  def signature(credentials: ApiCredential): SignatureClient = new SignatureClient(this, Some(credentials))
}

object SignatureClient {

  private val endPoint: String                                                        = "/signature"
  private implicit val format: OFormat[Signature]                                     = Signature.format
  private implicit val multiple_format: OFormat[SignatureMultiple]                    = SignatureMultiple.format
  private implicit val creation_format: OFormat[SignatureCreation]                    = SignatureCreation.format
  private implicit val multiple_creation_format: OFormat[SignatureMultipleCreation]   = SignatureMultipleCreation.format
  private implicit val signature_data_format: OFormat[SignatureData]                  = SignatureData.format
  private implicit val signature_multiple_data_format: OFormat[SignatureDataMultiple] = SignatureDataMultiple.format

}

class SignatureClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import SignatureClient._

  def sign(signature_creation: SignatureCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                      ExecutionContext
  ): Future[Either[ErrorResult, Signature]] = {
    ws.post[Signature](s"$endPoint", timeout, Json.toJson(signature_creation))
  }

  def signMultiple(signature_multiple_creation: SignatureMultipleCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                                       ExecutionContext
  ): Future[Either[ErrorResult, SignatureMultiple]] = {
    ws.post[SignatureMultiple](s"$endPoint/multiple", timeout, Json.toJson(signature_multiple_creation))
  }

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, Signature]] = {
    ws.get[Signature](s"$endPoint/$id", timeout)
  }

  def byIds(ids: List[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[Signature]]] = {
    ws.get[List[Signature]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def search(
    criteria:        SignatureSearch,
    entity_criteria: SignatureSearchForEntities,
    table_criteria:  TableSearch,
    timeout:         Long = defaultTimeOut
  )(implicit exec:   ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[SignatureData]]] = {
    ws.get[PaginatedSequence[SignatureData]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++
        LangUtils.productToQueryString(entity_criteria) ++
        LangUtils.productToQueryString(table_criteria)
    )
  }

  def searchMultiple(
    criteria:        SignatureSearch,
    entity_criteria: SignatureSearchForEntities,
    table_criteria:  TableSearch,
    timeout:         Long = defaultTimeOut
  )(implicit exec:   ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[SignatureDataMultiple]]] = {
    ws.get[PaginatedSequence[SignatureDataMultiple]](
      s"$endPoint/search/multiple",
      timeout,
      LangUtils.productToQueryString(criteria) ++
        LangUtils.productToQueryString(entity_criteria) ++
        LangUtils.productToQueryString(table_criteria)
    )
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Signature]] = {
    ws.delete[Signature](s"$endPoint/$id", timeout)
  }

  def deleteMany(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:             ExecutionContext
  ): Future[Either[ErrorResult, List[Signature]]] = {
    ws.delete[List[Signature]](s"$endPoint", timeout, Json.toJson(ids))
  }

  def deleteMultiple(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, SignatureMultiple]] = {
    ws.delete[SignatureMultiple](s"$endPoint/$id/multiple", timeout)
  }

  def exportCsv(
    criteria:        SignatureSearch,
    entity_criteria: SignatureSearchForEntities,
    table_criteria:  TableSearch,
    timeout:         Long = defaultTimeOut
  )(implicit exec:   ExecutionContext): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    ws.getStream(
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(
        entity_criteria
      ) ++ LangUtils.productToQueryString(table_criteria)
    )(exec, creds.withHeader("Content-Type", "application/csv"))
  }

  def createSignaturePosition(
    sp:      SignaturePositionOption,
    timeout: Long = defaultTimeOut
  )(implicit
    ec:      ExecutionContext
  ): Future[Either[ErrorResult, SignaturePosition]] = {
    ws.put[SignaturePosition](s"$endPoint/position", timeout, Json.toJson(sp))
  }

  def updateSignaturePosition(
    id:      String,
    sp:      SignaturePositionOption,
    timeout: Long = defaultTimeOut
  )(implicit
    ec:      ExecutionContext
  ): Future[Either[ErrorResult, SignaturePosition]] = {
    ws.post[SignaturePosition](s"$endPoint/position/$id", timeout, Json.toJson(sp))
  }
}
