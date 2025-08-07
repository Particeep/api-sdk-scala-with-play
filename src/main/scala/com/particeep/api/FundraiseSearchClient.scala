package com.particeep.api

import play.api.libs.json.OFormat

import scala.concurrent.{ ExecutionContext, Future }

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString

import com.particeep.api.core._
import com.particeep.api.models.fundraise.{ FundraiseData, FundraiseSearch }
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait FundraiseSearchCapability {
  self: WSClient =>

  def fundraise_search(credentials: ApiCredential): FundraiseSearchClient =
    new FundraiseSearchClient(this, credentials)
}

object FundraiseSearchClient {
  private val endPoint: String                        = "/fundraises"
  private implicit val format: OFormat[FundraiseData] = FundraiseData.format
}

class FundraiseSearchClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import FundraiseSearchClient._

  implicit val creds: ApiCredential = credentials

  def search(
    criteria:       FundraiseSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[FundraiseData]]] = {
    ws.get[PaginatedSequence[FundraiseData]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def exportCsv(
    criteria:       FundraiseSearch,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    ws.getStream(
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )(exec, creds.withHeader("Content-Type", "application/csv"))
  }

  def byIds(ids: List[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[FundraiseData]]] = {
    ws.get[List[FundraiseData]](s"$endPoint/byIds", timeout, List("ids" -> ids.mkString(",")))
  }
}
