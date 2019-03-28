package com.particeep.api

import com.particeep.api.core._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.models.stock._
import com.particeep.api.utils.LangUtils
import play.api.libs.json.Json

import scala.concurrent.{ ExecutionContext, Future }

trait StockCapability {
  self: WSClient =>

  val stock: StockClient = new StockClient(this)

  def stock(credentials: ApiCredential): StockClient = new StockClient(this, Some(credentials))
}

object StockClient {
  private val endPoint: String = "/stock"
  private implicit val format = Stock.format
  private implicit val creation_format = StockCreation.format
  private implicit val edition_format = StockEdition.format
  private implicit val composition_creation_format = StockCompositionCreation.format
}

class StockClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {

  import StockClient._

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Stock]] = {
    ws.get[Stock](s"$endPoint/$id", timeout)
  }

  def create(stock_creation: StockCreation, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Stock]] = {
    ws.put[Stock](s"$endPoint", timeout, Json.toJson(stock_creation))
  }

  def update(id: String, stock_edition: StockEdition, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Stock]] = {
    ws.post[Stock](s"$endPoint/$id", timeout, Json.toJson(stock_edition))
  }

  def search(
    criteria:       StockSearch,
    table_criteria: TableSearch,
    timeout:        Long        = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[Stock]]] = {
    ws.get[PaginatedSequence[Stock]](s"$endPoint/search", timeout, LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria))
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Stock]] = {
    ws.delete[Stock](s"$endPoint/$id", timeout)
  }

  def getComposition(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, List[Stock]]] = {
    ws.get[List[Stock]](s"$endPoint/$id/composition", timeout)
  }

  def addToComposition(
    id:                          String,
    stock_composition_creations: List[StockCompositionCreation],
    timeout:                     Long                           = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, List[Stock]]] = {
    ws.post[List[Stock]](s"$endPoint/$id/composition", timeout, Json.toJson(stock_composition_creations))
  }

  def removeFromComposition(id: String, ids: Seq[String], timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, List[Stock]]] = {
    ws.delete[List[Stock]](s"$endPoint/$id/composition", timeout, Json.toJson(""), List("ids" -> ids.mkString(",")))
  }

}
