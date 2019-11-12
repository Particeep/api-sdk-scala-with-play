package com.particeep.api

import com.particeep.api.core.{ApiCredential, EntityClient, WSClient, WithCredentials, WithWS}
import com.particeep.api.models.enums.FundStatus.FundStatus
import com.particeep.api.models.{ErrorResult, PaginatedSequence, TableSearch}
import com.particeep.api.models.fund.{Fund, FundCreation, FundEdition, FundSearch}
import com.particeep.api.utils.LangUtils
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}

trait FundCapability {
  self: WSClient =>

  val fund: FundClient = new FundClient(this)
  def fund(credentials: ApiCredential): FundClient = new FundClient(this, Some(credentials))
}

object FundClient {
  private val endPoint: String = "/fund"
  private implicit val creation_format = FundCreation.format
  private implicit val fund_format = Fund.format
  private implicit val fund_edition_format = FundEdition.format
}

class FundClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {
  import FundClient._

  def search(criteria: FundSearch, table_criteria: TableSearch, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[Fund]]] = {
    ws.get[PaginatedSequence[Fund]](s"$endPoint/search", timeout, LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria))
  }

  def create(fund_creation: FundCreation, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.put[Fund](s"$endPoint", timeout, Json.toJson(fund_creation))
  }

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.get[Fund](s"$endPoint/$id", timeout)
  }

  def update(id: String, fund_edition: FundEdition, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.post[Fund](s"$endPoint/$id", timeout, Json.toJson(fund_edition))
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.delete[Fund](s"$endPoint/$id", timeout)
  }

  def updateStatus(id: String, newStatus: FundStatus, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.post[Fund](s"$endPoint/$id/status/$newStatus", timeout, Json.toJson(""))
  }
}
