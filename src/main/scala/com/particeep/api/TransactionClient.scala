package com.particeep.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.util.ByteString
import play.api.libs.json.{ Format, Json, OFormat }
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.imports.{ ImportForm, ImportResult }
import com.particeep.api.models.transaction._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait TransactionCapability {
  self: WSClient =>

  val transaction: TransactionClient                             = new TransactionClient(this)
  def transaction(credentials: ApiCredential): TransactionClient = new TransactionClient(this, Some(credentials))
}

object TransactionClient {
  private val endPoint: String                                                      = "/transaction"
  private val endPoint_import: String                                               = "/import"
  private implicit val format: OFormat[Transaction]                                 = Transaction.format
  private implicit val creationFormat: OFormat[TransactionCreation]                 = TransactionCreation.format
  private implicit val editionFormat: OFormat[TransactionEdition]                   = TransactionEdition.format
  private implicit val transactionDataFormat: OFormat[TransactionData]              = TransactionData.format
  private implicit val importResultReads: Format[ImportResult[Transaction]]         = ImportResult.format[Transaction]
  private implicit val recurringFormat: OFormat[RecurringTransaction]               = RecurringTransaction.format
  private implicit val transactionStatsFormat: OFormat[TransactionSearchStatistics] = TransactionSearchStatistics.format
}

class TransactionClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import TransactionClient._

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.get[Transaction](s"$endPoint/$id", timeout)
  }

  def byIds(ids: List[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[Transaction]]] = {
    ws.get[List[Transaction]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def create(transaction_creation: TransactionCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                          ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.put[Transaction](s"$endPoint", timeout, Json.toJson(transaction_creation))
  }

  def update(id: String, transaction_edition: TransactionEdition, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/$id", timeout, Json.toJson(transaction_edition))
  }

  def search(
    criteria:            TransactionSearch,
    criteria_additional: TransactionSearchAdditional,
    table_criteria:      TableSearch,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[TransactionData]]] = {
    ws.get[PaginatedSequence[TransactionData]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(
        criteria_additional
      ) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def search_stats(
    criteria:            TransactionSearch,
    criteria_additional: TransactionSearchAdditional,
    table_criteria:      TableSearch,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, TransactionSearchStatistics]] = {
    ws.get[TransactionSearchStatistics](
      s"$endPoint/search_stats",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(
        criteria_additional
      ) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def cancel(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.delete[Transaction](s"$endPoint/$id/cancel", timeout)
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.delete[Transaction](s"$endPoint/$id", timeout)
  }

  def deleteMulti(ids: String, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, List[Transaction]]] = {
    ws.delete[List[Transaction]](s"$endPoint/multiple/$ids", timeout)
  }

  def importFromCsv(csv: File, importForm: Option[ImportForm] = None, timeout: Long = defaultImportTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, ImportResult[Transaction]]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )

    ws.postFile[ImportResult[Transaction]](s"$endPoint_import/transaction/csv", timeout, csv, "text/csv", bodyParts)
  }

  def exportCsv(
    criteria:            TransactionSearch,
    criteria_additional: TransactionSearchAdditional,
    table_criteria:      TableSearch,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    ws.getStream(
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(
        criteria_additional
      ) ++ LangUtils.productToQueryString(table_criteria)
    )(exec, creds.withHeader("Content-Type", "application/csv"))
  }

  def recurringById(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:               ExecutionContext
  ): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.get[RecurringTransaction](s"$endPoint/recurring/$id", timeout)
  }

  def recurringSearch(
    criteria:       RecurringTransactionsSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[RecurringTransaction]]] = {
    ws.get[PaginatedSequence[RecurringTransaction]](
      s"$endPoint/recurring/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }
}
