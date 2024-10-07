package com.particeep.api

import play.api.libs.json.{ Format, Json, OFormat }
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.enums.FundraiseStatus
import com.particeep.api.models.fundraise.loan._
import com.particeep.api.models.imports.{ ImportForm, ImportResult }
import com.particeep.api.models.payment.{ PayResult, PaymentCbCreation }
import com.particeep.api.models.transaction.{
  RecurringTransaction,
  RecurringTransactionCreation,
  Transaction,
  TransactionSearch
}
import com.particeep.api.models.user.User
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait FundraiseLoanCapability {
  self: WSClient =>

  val fundraise_loan: FundraiseLoanClient                             = new FundraiseLoanClient(this)
  def fundraise_loan(credentials: ApiCredential): FundraiseLoanClient = new FundraiseLoanClient(this, Some(credentials))
}

object FundraiseLoanClient {
  private val endPoint: String                                                                        = "/loan"
  private val endPoint_import: String                                                                 = "/import"
  private implicit val format: OFormat[FundraiseLoan]                                                 = FundraiseLoan.format
  private implicit val creation_format: OFormat[FundraiseLoanCreation]                                = FundraiseLoanCreation.format
  private implicit val edition_format: OFormat[FundraiseLoanEdition]                                  = FundraiseLoanEdition.format
  private implicit val running_edition_format: OFormat[FundraiseLoanRunningEdition]                   = FundraiseLoanRunningEdition.format
  private implicit val repayment_info_format: OFormat[RepaymentInfo]                                  = RepaymentInfo.format
  private implicit val repayment_with_date_format: OFormat[RepaymentWithDate]                         = RepaymentWithDate.format
  private implicit val repayment_info_vector_format: OFormat[RepaymentInfoVector]                     = RepaymentInfoVector.format
  private implicit val loan_repayment_schedule_format: OFormat[LoanRepaymentSchedule]                 = LoanRepaymentSchedule.format
  private implicit val loan_repayment_schedule_and_user_format: Format[(LoanRepaymentSchedule, User)] =
    LoanRepaymentSchedule.loan_repayment_schedule_and_user_format
  private implicit val scheduled_payment_format: OFormat[ScheduledPayment]                            = ScheduledPayment.format
  private implicit val lend_format: OFormat[Lend]                                                     = Lend.format
  private implicit val transaction_format: OFormat[Transaction]                                       = Transaction.format
  private implicit val lend_creation_format: OFormat[LendCreation]                                    = LendCreation.format
  private implicit val lend_option_format: OFormat[LendOption]                                        = LendOption.format
  private implicit val estimate_borrower_info_format: OFormat[EstimateBorrowerInfo]                   = EstimateBorrowerInfo.format
  private implicit val importResultReads: Format[ImportResult[FundraiseLoan]]                         = ImportResult.format[FundraiseLoan]
  private implicit val recurring_transaction_format: OFormat[RecurringTransaction]                    = RecurringTransaction.format
  private implicit val recurring_transaction_creation_format: OFormat[RecurringTransactionCreation]   =
    RecurringTransactionCreation.format
  private implicit val payment_cb_creation_format: OFormat[PaymentCbCreation]                         = PaymentCbCreation.format
  private implicit val pay_result_format: OFormat[PayResult]                                          = PayResult.format
}

class FundraiseLoanClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import FundraiseLoanClient._

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, FundraiseLoan]] = {
    ws.get[FundraiseLoan](s"$endPoint/fundraise/$id", timeout)
  }

  def byIds(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[FundraiseLoan]]] = {
    ws.get[List[FundraiseLoan]](s"$endPoint/fundraise", timeout, List("ids" -> ids.mkString(",")))
  }

  def create(fundraise_loan_creation: FundraiseLoanCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                             ExecutionContext
  ): Future[Either[ErrorResult, FundraiseLoan]] = {
    ws.put[FundraiseLoan](s"$endPoint/fundraise", timeout, Json.toJson(fundraise_loan_creation))
  }

  def update(id: String, fundraise_loan_edition: FundraiseLoanEdition, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, FundraiseLoan]] = {
    ws.post[FundraiseLoan](s"$endPoint/fundraise/$id", timeout, Json.toJson(fundraise_loan_edition))
  }

  def updateRunning(
    id:                             String,
    fundraise_loan_running_edition: FundraiseLoanRunningEdition,
    timeout:                        Long = defaultTimeOut
  )(implicit exec:                  ExecutionContext): Future[Either[ErrorResult, FundraiseLoan]] = {
    ws.post[FundraiseLoan](s"$endPoint/fundraise/running/$id", timeout, Json.toJson(fundraise_loan_running_edition))
  }

  def search(
    criteria:       FundraiseLoanSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[FundraiseLoan]]] = {
    ws.get[PaginatedSequence[FundraiseLoan]](
      s"$endPoint/fundraises",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, FundraiseLoan]] = {
    ws.delete[FundraiseLoan](s"$endPoint/fundraise/$id", timeout)
  }

  def updateStatus(id: String, new_status: FundraiseStatus, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, FundraiseLoan]] = {
    ws.post[FundraiseLoan](s"$endPoint/fundraise/$id/status/$new_status", timeout, Json.toJson(""))
  }

  def getLenderRepaymentScheduleEstimation(
    id:             String,
    repayment_info: RepaymentInfo,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, List[RepaymentWithDate]]] = {
    ws.post[List[RepaymentWithDate]](
      s"$endPoint/fundraise/$id/info/estimate/lender",
      timeout,
      Json.toJson(repayment_info)
    )
  }

  def getBorrowerRepaymentScheduleEstimation(
    fundraise_id:           String,
    estimate_borrower_info: EstimateBorrowerInfo,
    timeout:                Long = defaultTimeOut
  )(implicit exec:          ExecutionContext): Future[Either[ErrorResult, List[RepaymentWithDate]]] = {
    ws.post[List[RepaymentWithDate]](
      s"$endPoint/fundraise/$fundraise_id/info/estimate/borrower",
      timeout,
      Json.toJson(estimate_borrower_info)
    )
  }

  def getLenderRepaymentSchedule(id: String, user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                            ExecutionContext
  ): Future[Either[ErrorResult, List[RepaymentWithDate]]] = {
    ws.post[List[RepaymentWithDate]](s"$endPoint/fundraise/$id/info/user/$user_id", timeout, Json.toJson(""))
  }

  def getTransactionRepaymentSchedule(id: String, transaction_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                                 ExecutionContext
  ): Future[Either[ErrorResult, List[LoanRepaymentSchedule]]] = {
    ws.get[List[LoanRepaymentSchedule]](s"$endPoint/fundraise/$id/repayment/transaction/$transaction_id", timeout)
  }

  def getBorrowerRepaymentSchedule(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                              ExecutionContext
  ): Future[Either[ErrorResult, List[RepaymentWithDate]]] = {
    ws.post[List[RepaymentWithDate]](s"$endPoint/fundraise/$id/info/borrower", timeout, Json.toJson(""))
  }

  def getRepaymentScheduleByLender(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                                   ExecutionContext
  ): Future[Either[ErrorResult, List[LoanRepaymentSchedule]]] = {
    ws.get[List[LoanRepaymentSchedule]](s"$endPoint/repayment/user/$user_id", timeout)
  }

  def getBorrowerRepaymentScheduleDetail(
    id:            String,
    payment_day:   Int,
    payment_month: Int,
    payment_year:  Int,
    timeout:       Long = defaultTimeOut
  )(
    implicit exec: ExecutionContext
  ): Future[Either[ErrorResult, List[(LoanRepaymentSchedule, User)]]] = {
    ws.get[List[(LoanRepaymentSchedule, User)]](
      s"$endPoint/fundraise/$id/detail/borrower/$payment_day/$payment_month/$payment_year",
      timeout
    )
  }

  def payOfflineBorrowerRepaymentSchedule(
    id:            String,
    payment_day:   Int,
    payment_month: Int,
    payment_year:  Int,
    timeout:       Long = defaultTimeOut
  )(
    implicit exec: ExecutionContext
  ): Future[Either[ErrorResult, Seq[LoanRepaymentSchedule]]] = {
    ws.post[Seq[LoanRepaymentSchedule]](
      s"$endPoint/fundraise/$id/pay-offline/borrower/$payment_day/$payment_month/$payment_year",
      timeout,
      Json.toJson("")
    )
  }

  def generateRepaymentSchedule(
    id:            String,
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, List[ScheduledPayment]]] = {
    ws.post[List[ScheduledPayment]](s"$endPoint/fundraise/$id/schedule/define", timeout, Json.toJson(""))
  }

  def generateCustomRepaymentSchedule(
    id:                    String,
    repayment_info_vector: RepaymentInfoVector,
    timeout:               Long = defaultTimeOut
  )(implicit exec:         ExecutionContext): Future[Either[ErrorResult, List[ScheduledPayment]]] = {
    ws.post[List[ScheduledPayment]](
      s"$endPoint/fundraise/$id/schedule/update",
      timeout,
      Json.toJson(repayment_info_vector)
    )
  }

  def allLendsOnFundraise(
    id:             String,
    criteria:       TransactionSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[Lend]]] = {
    ws.get[PaginatedSequence[Lend]](
      s"$endPoint/fundraise/$id/lends",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def allLendsByUser(
    user_id:        String,
    criteria:       TransactionSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[Transaction]]] = {
    ws.get[PaginatedSequence[Transaction]](
      s"$endPoint/fundraise/lends/user/$user_id",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def lend(id: String, lend_creation: LendCreation, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/fundraise/$id/lend", timeout, Json.toJson(lend_creation))
  }

  def updateLend(id: String, transaction_id: String, lend_option: LendOption, timeout: Long = defaultTimeOut)(implicit
    exec:            ExecutionContext
  ): Future[Either[ErrorResult, Transaction]] = {
    ws.post[Transaction](s"$endPoint/fundraise/$id/lend/$transaction_id", timeout, Json.toJson(lend_option))
  }

  def pay(
    id:                  String,
    transaction_id:      String,
    payment_cb_creation: PaymentCbCreation,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, PayResult]] = {
    ws.post[PayResult](s"$endPoint/fundraise/$id/pay/$transaction_id", timeout, Json.toJson(payment_cb_creation))
  }

  def importFromCsv(csv: File, importForm: Option[ImportForm] = None, timeout: Long = defaultImportTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, ImportResult[FundraiseLoan]]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )

    ws.postFile[ImportResult[FundraiseLoan]](
      s"$endPoint_import/fundraise-loan/csv",
      timeout,
      csv,
      "text/csv",
      bodyParts
    )
  }

  def recurringLend(
    id:                           String,
    recurring_transaction_create: RecurringTransactionCreation,
    timeout:                      Long = defaultTimeOut
  )(implicit exec:                ExecutionContext): Future[Either[ErrorResult, RecurringTransaction]] = {
    ws.post[RecurringTransaction](
      s"$endPoint/fundraise/$id/lend/recurring",
      timeout,
      Json.toJson(recurring_transaction_create)
    )
  }
}
