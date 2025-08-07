package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.wallet._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait WalletCapability {
  self: WSClient =>

  def wallet(credentials: ApiCredential): WalletClient = new WalletClient(this, credentials)
}

object WalletClient {
  private val endPoint: String                                                                 = "/wallet"
  private implicit val format: OFormat[Wallet]                                                 = Wallet.format
  private implicit val creation_format: OFormat[WalletCreation]                                = WalletCreation.format
  private implicit val cash_in_format: OFormat[CashIn]                                         = CashIn.format
  private implicit val cash_out_format: OFormat[CashOut]                                       = CashOut.format
  private implicit val transaction_format: OFormat[TransactionWallet]                          = TransactionWallet.format
  private implicit val transaction_data_format: OFormat[TransactionWalletData]                 = TransactionWalletData.format
  private implicit val transfer_format: OFormat[WalletTransfer]                                = WalletTransfer.format
  private implicit val bank_account_format: OFormat[BankAccount]                               = BankAccount.format
  private implicit val bank_account_creation_format: OFormat[BankAccountCreation]              = BankAccountCreation.format
  private implicit val cashin_bank_account_format: OFormat[CashInBankAccount]                  = CashInBankAccount.format
  private implicit val cashin_bank_account_creation_format: OFormat[CashInBankAccountCreation] =
    CashInBankAccountCreation.format
  private implicit val transaction_wallet_fees_option: OFormat[TransactionWalletFeesOpt]       =
    TransactionWalletFeesOpt.format
}

class WalletClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import WalletClient._

  implicit val creds: ApiCredential = credentials

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, Wallet]] = {
    ws.get[Wallet](s"$endPoint/$id", timeout)
  }

  def byTargetIdAndType(owner_id: String, owner_type: String, timeout: Long = defaultTimeOut)(implicit
    exec:                         ExecutionContext
  ): Future[Either[ErrorResult, Wallet]] = {
    ws.get[Wallet](s"$endPoint/owner/$owner_id/$owner_type", timeout)
  }

  def consumerWallet(timeout: Long = defaultTimeOut)(implicit
    exec:                     ExecutionContext
  ): Future[Either[ErrorResult, Wallet]] = {
    ws.get[Wallet](s"$endPoint/consumer", timeout)
  }

  def create(wallet_creation: WalletCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                     ExecutionContext
  ): Future[Either[ErrorResult, Wallet]] = {
    ws.put[Wallet](s"$endPoint", timeout, Json.toJson(wallet_creation))
  }

  def cashin(id: String, cash_in: CashIn, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, String]] = {
    ws.post[String](s"$endPoint/$id/cashin", timeout, Json.toJson(cash_in))
  }

  def withdraw(id: String, cash_out: CashOut, timeout: Long = defaultTimeOut)(implicit
    exec:          ExecutionContext
  ): Future[Either[ErrorResult, TransactionWallet]] = {
    ws.post[TransactionWallet](s"$endPoint/$id/cashout", timeout, Json.toJson(cash_out))
  }

  def transfer(transfer: WalletTransfer, timeout: Long = defaultTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, TransactionWalletFeesOpt]] = {
    ws.post[TransactionWalletFeesOpt](s"$endPoint/transfer", timeout, Json.toJson(transfer))
  }

  def search(
    criteria:       TransactionWalletSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(
    implicit exec:  ExecutionContext
  ): Future[Either[ErrorResult, PaginatedSequence[TransactionWalletData]]] = {
    ws.get[PaginatedSequence[TransactionWalletData]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def addBankAccount(id: String, bank_account_creation: BankAccountCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, BankAccount]] = {
    ws.put[BankAccount](s"$endPoint/$id/bankaccount", timeout, Json.toJson(bank_account_creation))
  }

  def addBankAccountOffline(
    target_id:           String,
    bankAccountCreation: BankAccountCreation,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, BankAccount]] = {
    ws.put[BankAccount](s"$endPoint/bankaccount/$target_id", timeout, Json.toJson(bankAccountCreation))
  }

  def updateBankAccount(
    bankaccount_id:      String,
    bank_account_update: BankAccountCreation,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, BankAccount]] = {
    ws.post[BankAccount](s"$endPoint/bankaccount/$bankaccount_id", timeout, Json.toJson(bank_account_update))
  }

  def searchBankAccounts(
    criteria:       BankAccountSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[BankAccount]]] = {
    ws.get[PaginatedSequence[BankAccount]](
      s"$endPoint/bankaccount/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def deleteBankAccountOffline(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                          ExecutionContext
  ): Future[Either[ErrorResult, Int]] = {
    ws.delete[Int](s"$endPoint/bankaccount/$id", timeout)
  }

  def getBankAccountsByWalletId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                           ExecutionContext
  ): Future[Either[ErrorResult, Seq[BankAccount]]] = {
    ws.get[Seq[BankAccount]](s"$endPoint/$id/bankaccount", timeout)
  }

  def cashinBankAccount(
    id:                            String,
    cash_in_bank_account_creation: CashInBankAccountCreation,
    timeout:                       Long = defaultTimeOut
  )(implicit exec:                 ExecutionContext): Future[Either[ErrorResult, CashInBankAccount]] = {
    ws.post[CashInBankAccount](s"$endPoint/$id/cashin/bankAccount", timeout, Json.toJson(cash_in_bank_account_creation))
  }
}
