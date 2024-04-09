package com.particeep.api

import play.api.libs.json.{ Format, Json, OFormat }
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.enums.FundraiseStatus.FundraiseStatus
import com.particeep.api.models.fundraise.equity._
import com.particeep.api.models.imports.{ ImportForm, ImportResult }
import com.particeep.api.models.transaction.{
  Investment,
  RecurringTransaction,
  RecurringTransactionCreation,
  Transaction,
  TransactionSearch
}
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait FundraiseEquityCapability {
  self: WSClient =>

  val fundraise_equity: FundraiseEquityClient = new FundraiseEquityClient(this)

  def fundraise_equity(credentials: ApiCredential): FundraiseEquityClient =
    new FundraiseEquityClient(this, Some(credentials))
}

object FundraiseEquityClient {
  private val endPoint: String                                                                      = "/equity"
  private val endPoint_import: String                                                               = "/import"
  private implicit val format: OFormat[FundraiseEquity]                                             = FundraiseEquity.format
  private implicit val creation_format: OFormat[FundraiseEquityCreation]                            = FundraiseEquityCreation.format
  private implicit val edition_format: OFormat[FundraiseEquityEdition]                              = FundraiseEquityEdition.format
  private implicit val running_edition_format: OFormat[FundraiseEquityRunningEdition]               =
    FundraiseEquityRunningEdition.format
  private implicit val investment_format: OFormat[Investment]                                       = Investment.format
  private implicit val transaction_format: OFormat[Transaction]                                     = Transaction.format
  private implicit val investment_creation_format: OFormat[InvestmentCreation]                      = InvestmentCreation.format
  private implicit val investment_option_format: OFormat[InvestmentOption]                          = InvestmentOption.format
  private implicit val importResultReads: Format[ImportResult[FundraiseEquity]]                     = ImportResult.format[FundraiseEquity]
  private implicit val recurring_transaction_format: OFormat[RecurringTransaction]                  = RecurringTransaction.format
  private implicit val recurring_transaction_creation_format: OFormat[RecurringTransactionCreation] =
    RecurringTransactionCreation.format
}

class FundraiseEquityClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import FundraiseEquityClient._

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, FundraiseEquity]] = {
    ws.get[FundraiseEquity](s"$endPoint/fundraise/$id", timeout)
  }

  def byIds(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[FundraiseEquity]]] = {
    ws.get[List[FundraiseEquity]](s"$endPoint/fundraise", timeout, List("ids" -> ids.mkString(",")))
  }

  def create(fundraise_equity_creation: FundraiseEquityCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                               ExecutionContext
  ): Future[Either[ErrorResult, FundraiseEquity]] = {
    ws.put[FundraiseEquity](s"$endPoint/fundraise", timeout, Json.toJson(fundraise_equity_creation))
  }

  def update(id: String, fundraise_equity_edition: FundraiseEquityEdition, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, FundraiseEquity]] = {
    ws.post[FundraiseEquity](s"$endPoint/fundraise/$id", timeout, Json.toJson(fundraise_equity_edition))
  }

  def updateRunning(
    id:                               String,
    fundraise_equity_running_edition: FundraiseEquityRunningEdition,
    timeout:                          Long = defaultTimeOut
  )(implicit exec:                    ExecutionContext): Future[Either[ErrorResult, FundraiseEquity]] = {
    ws.post[FundraiseEquity](s"$endPoint/fundraise/running/$id", timeout, Json.toJson(fundraise_equity_running_edition))
  }

  def search(
    criteria:       FundraiseEquitySearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[FundraiseEquity]]] = {
    ws.get[PaginatedSequence[FundraiseEquity]](
      s"$endPoint/fundraises",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, FundraiseEquity]] = {
    ws.delete[FundraiseEquity](s"$endPoint/fundraise/$id", timeout)
  }

  def updateStatus(id: String, new_status: FundraiseStatus, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, FundraiseEquity]] = {
    ws.post[FundraiseEquity](s"$endPoint/fundraise/$id/status/$new_status", timeout, Json.toJson(""))
  }

  def allInvestmentsOnFundraise(
    id:             String,
    criteria:       TransactionSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[Investment]]] = {
    ws.get[PaginatedSequence[Investment]](
      s"$endPoint/fundraise/$id/investments",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def allInvestmentsByUser(
    user_id:        String,
    criteria:       TransactionSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[Transaction]]] = {
    ws.get[PaginatedSequence[Transaction]](
      s"$endPoint/fundraise/investments/user/$user_id",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def invest(id: String, investment_creation: InvestmentCreation, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/fundraise/$id/invest", timeout, Json.toJson(investment_creation))
  }

  def updateInvest(
    id:                String,
    transaction_id:    String,
    investment_option: InvestmentOption,
    timeout:           Long = defaultTimeOut
  )(implicit exec:     ExecutionContext): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/fundraise/$id/invest/$transaction_id", timeout, Json.toJson(investment_option))
  }

  def importFromCsv(csv: File, importForm: Option[ImportForm] = None, timeout: Long = defaultImportTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, ImportResult[FundraiseEquity]]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )

    ws.postFile[ImportResult[FundraiseEquity]](
      s"$endPoint_import/fundraise-equity/csv",
      timeout,
      csv,
      "text/csv",
      bodyParts
    )
  }

  def recurringEquity(
    id:                           String,
    recurring_transaction_create: RecurringTransactionCreation,
    timeout:                      Long = defaultTimeOut
  )(implicit exec:                ExecutionContext): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.post[RecurringTransaction](
      s"$endPoint/fundraise/$id/equity/recurring",
      timeout,
      Json.toJson(recurring_transaction_create)
    )
  }
}
