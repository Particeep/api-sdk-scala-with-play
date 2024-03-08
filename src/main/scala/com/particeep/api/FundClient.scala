package com.particeep.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.util.ByteString
import play.api.libs.json.Json

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core.{ ApiCredential, EntityClient, WSClient, WithCredentials, WithWS }
import com.particeep.api.models.enums.FundStatus.FundStatus
import com.particeep.api.models.fund._
import com.particeep.api.models.transaction.{
  Investment,
  RecurringTransaction,
  RecurringTransactionCreation,
  Transaction,
  TransactionSearch
}
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait FundCapability {
  self: WSClient =>

  val fund: FundClient                             = new FundClient(this)
  def fund(credentials: ApiCredential): FundClient = new FundClient(this, Some(credentials))
}

object FundClient {
  private val endPoint: String                      = "/fund"
  private implicit val creation_format              = FundCreation.format
  private implicit val fund_format                  = Fund.format
  private implicit val fund_edition_format          = FundEdition.format
  private implicit val fund_data_format             = FundData.format
  private implicit val investment_format            = Investment.format
  private implicit val investment_creation_format   = InvestmentCreation.format
  private implicit val investment_option_format     = InvestmentOption.format
  private implicit val transaction_edit_part_format = TransactionEditPart.format
  private implicit val transaction_format           = Transaction.format
  private implicit val recurring_transaction_format = RecurringTransaction.format
}

class FundClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials
    with EntityClient {
  import FundClient._

  def create(fund_creation: FundCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                   ExecutionContext
  ): Future[Either[ErrorResult, Fund]] = {
    ws.put[Fund](s"$endPoint", timeout, Json.toJson(fund_creation))
  }

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, Fund]] = {
    ws.get[Fund](s"$endPoint/$id", timeout)
  }

  def byIds(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[Fund]]] = {
    ws.get[List[Fund]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def update(id: String, fund_edition: FundEdition, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Fund]] = {
    ws.post[Fund](s"$endPoint/$id", timeout, Json.toJson(fund_edition))
  }

  def updateStatus(id: String, new_status: FundStatus, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, Fund]] = {
    ws.post[Fund](s"$endPoint/$id/status/$new_status", timeout, Json.toJson(""))
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Fund]] = {
    ws.delete[Fund](s"$endPoint/$id", timeout)
  }

  def deletes(ids: String, timeout: Long = defaultTimeOut)(implicit
    exec:          ExecutionContext
  ): Future[Either[ErrorResult, List[Fund]]] = {
    ws.delete[List[Fund]](s"$endPoint/multiple/$ids", timeout)
  }

  def search(criteria: FundSearch, table_criteria: TableSearch, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, PaginatedSequence[FundData]]] = {
    ws.get[PaginatedSequence[FundData]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def exportCsv(
    criteria:       FundSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    ws.getStream(
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )(exec, creds.withHeader("Content-Type", "application/csv"))
  }

  def invest(id: String, investment_creation: InvestmentCreation, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/$id/invest", timeout, Json.toJson(investment_creation))
  }

  def updateInvest(
    id:                String,
    transaction_id:    String,
    investment_option: InvestmentOption,
    timeout:           Long = defaultTimeOut
  )(implicit exec:     ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/$id/invest/$transaction_id", timeout, Json.toJson(investment_option))
  }

  def allInvestmentByFund(
    id:             String,
    criteria:       TransactionSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[Investment]]] = {
    ws.get[PaginatedSequence[Investment]](
      s"$endPoint/$id/investments",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def editTransactionsPricePerShare(
    id:                    String,
    transaction_edit_part: TransactionEditPart,
    timeout:               Long = defaultTimeOut
  )(implicit exec:         ExecutionContext): Future[Either[ErrorResult, List[Transaction]]] = {
    ws.post[List[Transaction]](s"$endPoint/$id/edit/transaction/share", timeout, Json.toJson(transaction_edit_part))
  }

  def recurringLend(
    id:                           String,
    recurring_transaction_create: RecurringTransactionCreation,
    timeout:                      Long = defaultTimeOut
  )(implicit exec:                ExecutionContext): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.post[RecurringTransaction](s"$endPoint/$id/lend/recurring", timeout, Json.toJson(recurring_transaction_create))
  }
}
