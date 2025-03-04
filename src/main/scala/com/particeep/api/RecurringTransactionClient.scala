package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.transaction._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait RecurringTransactionCapability {
  self: WSClient =>

  val recurring_transaction: RecurringTransactionClient                             = new RecurringTransactionClient(this)
  def recurring_transaction(credentials: ApiCredential): RecurringTransactionClient =
    new RecurringTransactionClient(this, Some(credentials))
}

object RecurringTransactionClient {
  private val endPoint: String                                             = "/transaction"
  private implicit val format: OFormat[RecurringTransaction]               = RecurringTransaction.format
  private implicit val editionFormat: OFormat[RecurringTransactionEdition] = RecurringTransactionEdition.format
}

class RecurringTransactionClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import RecurringTransactionClient._

  def byTransactionId(transaction_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                             ExecutionContext
  ): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.get[RecurringTransaction](s"$endPoint/recurring/$transaction_id", timeout)
  }

  def update(
    transaction_id:                String,
    recurring_transaction_edition: RecurringTransactionEdition,
    timeout:                       Long = defaultTimeOut
  )(implicit
    exec:                          ExecutionContext
  ): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.post[RecurringTransaction](
      s"$endPoint/recurring/$transaction_id",
      timeout,
      Json.toJson(recurring_transaction_edition)
    )
  }

  def delete(transaction_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                    ExecutionContext
  ): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.delete[RecurringTransaction](s"$endPoint/recurring/$transaction_id", timeout)
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
