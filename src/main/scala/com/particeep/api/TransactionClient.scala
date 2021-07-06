package com.particeep.api

import java.io.File

import com.particeep.api.core._
import com.particeep.api.models.imports.ImportResult
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.models.transaction._
import com.particeep.api.utils.LangUtils
import play.api.libs.json.Json
import com.particeep.api.models.imports.ImportForm
import com.ning.http.client.multipart.StringPart
import play.api.libs.iteratee.Enumerator

import scala.concurrent.{ ExecutionContext, Future }

trait TransactionCapability {
  self: WSClient =>

  val transaction: TransactionClient = new TransactionClient(this)
  def transaction(credentials: ApiCredential): TransactionClient = new TransactionClient(this, Some(credentials))
}

object TransactionClient {
  private val endPoint: String = "/transaction"
  private val endPoint_import: String = "/import"
  private implicit val format = Transaction.format
  private implicit val creationFormat = TransactionCreation.format
  private implicit val editionFormat = TransactionEdition.format
  private implicit val transactionDataFormat = TransactionData.format
  private implicit val importResultReads = ImportResult.format[Transaction]
  private implicit val recurringFormat = RecurringTransaction.format
}

class TransactionClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {

  import TransactionClient._

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.get[Transaction](s"$endPoint/$id", timeout)
  }

  def byIds(ids: List[String], timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, List[Transaction]]] = {
    ws.get[List[Transaction]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def create(transaction_creation: TransactionCreation, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.put[Transaction](s"$endPoint", timeout, Json.toJson(transaction_creation))
  }

  def update(id: String, transaction_edition: TransactionEdition, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/$id", timeout, Json.toJson(transaction_edition))
  }

  def search(
    criteria:       TransactionSearch,
    table_criteria: TableSearch,
    timeout:        Long              = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[TransactionData]]] = {
    ws.get[PaginatedSequence[TransactionData]](s"$endPoint/search", timeout, LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria))
  }

  def cancel(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.delete[Transaction](s"$endPoint/$id/cancel", timeout)
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.delete[Transaction](s"$endPoint/$id", timeout)
  }

  def deleteMulti(ids: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, List[Transaction]]] = {
    ws.delete[List[Transaction]](s"$endPoint/multiple/$ids", timeout)
  }

  def importFromCsv(csv: File, importForm: Option[ImportForm] = None, timeout: Long = defaultImportTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, ImportResult[Transaction]]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )

    ws.postFile[ImportResult[Transaction]](s"$endPoint_import/transaction/csv", timeout, csv, "text/csv", bodyParts)
  }

  def export(
    criteria:       TransactionSearch,
    table_criteria: TableSearch,
    timeout:        Long              = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, Enumerator[Array[Byte]]]] = {
    ws.getStream(
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )(exec, creds.withHeader("Content-Type", "application/csv"))
  }

  def recurringById(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.get[RecurringTransaction](s"$endPoint/recurring/$id", timeout)
  }

  def recurringSearch(
    criteria:       RecurringTransactionsSearch,
    table_criteria: TableSearch,
    timeout:        Long                        = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[RecurringTransaction]]] = {
    ws.get[PaginatedSequence[RecurringTransaction]](s"$endPoint/recurring/search", timeout, LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria))
  }
}
