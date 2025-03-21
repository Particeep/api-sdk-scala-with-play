package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString

import com.particeep.api.core.{ ApiCredential, EntityClient, WSClient, WithCredentials, WithWS }
import com.particeep.api.models.enums.FundStatus
import com.particeep.api.models.fund._
import com.particeep.api.models.payment.{ PayResult, PaymentCbCreation }
import com.particeep.api.models.transaction.{ Investment, Transaction, TransactionSearch }
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait FundCapability {
  self: WSClient =>

  val fund: FundClient                             = new FundClient(this)
  def fund(credentials: ApiCredential): FundClient = new FundClient(this, Some(credentials))
}

object FundClient {
  private val endPoint: String                                                    = "/fund"
  private implicit val creation_format: OFormat[FundCreation]                     = FundCreation.format
  private implicit val fund_format: OFormat[Fund]                                 = Fund.format
  private implicit val fund_edition_format: OFormat[FundEdition]                  = FundEdition.format
  private implicit val fund_data_format: OFormat[FundData]                        = FundData.format
  private implicit val investment_format: OFormat[Investment]                     = Investment.format
  private implicit val investment_creation_format: OFormat[InvestmentCreation]    = InvestmentCreation.format
  private implicit val investment_option_format: OFormat[InvestmentOption]        = InvestmentOption.format
  private implicit val transaction_edit_part_format: OFormat[TransactionEditPart] = TransactionEditPart.format
  private implicit val transaction_format: OFormat[Transaction]                   = Transaction.format
  private implicit val payment_cb_creation_format: OFormat[PaymentCbCreation]     = PaymentCbCreation.format
  private implicit val pay_result_format: OFormat[PayResult]                      = PayResult.format
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

  def pay(
    id:                  String,
    transaction_id:      String,
    payment_cb_creation: PaymentCbCreation,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, PayResult]] = {
    ws.post[PayResult](s"$endPoint/$id/pay/$transaction_id", timeout, Json.toJson(payment_cb_creation))
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
}
