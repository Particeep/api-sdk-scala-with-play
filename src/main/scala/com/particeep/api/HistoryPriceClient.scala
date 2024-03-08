package com.particeep.api

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models._
import com.particeep.api.models.history_price.{ HistoryPrice, HistoryPriceSearch }
import com.particeep.api.utils.LangUtils
import play.api.libs.json._

trait HistoryPriceCapability {
  self: WSClient =>

  val history_price: HistoryPriceClient = new HistoryPriceClient(this)

  def history_price(credentials: ApiCredential): HistoryPriceClient = new HistoryPriceClient(this, Some(credentials))
}

object HistoryPriceClient {
  private val endPoint: String                       = "/history/price"
  private implicit val format: OFormat[HistoryPrice] = HistoryPrice.format
}

class HistoryPriceClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import HistoryPriceClient._

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
