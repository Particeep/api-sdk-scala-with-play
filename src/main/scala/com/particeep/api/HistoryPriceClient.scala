package com.particeep.api

import play.api.libs.json.OFormat

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models._
import com.particeep.api.models.history_price.{ HistoryPrice, HistoryPriceSearch }
import com.particeep.api.utils.LangUtils

trait HistoryPriceCapability {
  self: WSClient =>

  def history_price(credentials: ApiCredential): HistoryPriceClient = new HistoryPriceClient(this, credentials)
}

object HistoryPriceClient {
  private val endPoint: String                       = "/history/price"
  private implicit val format: OFormat[HistoryPrice] = HistoryPrice.format
}

class HistoryPriceClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import HistoryPriceClient._

  implicit val creds: ApiCredential = credentials

  def search(criteria: HistoryPriceSearch, table_criteria: TableSearch, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, PaginatedSequence[HistoryPrice]]] = {
    ws.get[PaginatedSequence[HistoryPrice]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }
}
