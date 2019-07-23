package com.particeep.api

import com.particeep.api.core.{ ApiCredential, EntityClient, WSClient, WithCredentials, WithWS }
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.fund.{ Fund, FundCreation, FundEdition }
import play.api.libs.json.Json

import scala.concurrent.{ ExecutionContext, Future }

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

  def create(fund_creation: FundCreation, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.put[Fund](s"$endPoint", timeout, Json.toJson(fund_creation))
  }

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.get[Fund](s"$endPoint/$id", timeout)
  }

  def update(id: String, fund_edition: FundEdition, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Fund]] = {
    ws.post[Fund](s"$endPoint/$id", timeout, Json.toJson(fund_edition))
  }
}