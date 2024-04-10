package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.news._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait NewsCapability {
  self: WSClient =>

  val news: NewsClient                             = new NewsClient(this)
  def news(credentials: ApiCredential): NewsClient = new NewsClient(this, Some(credentials))
}

object NewsClient {

  private val endPoint: String = "/newsfeed"

  private implicit val format: OFormat[News]                  = News.format
  private implicit val creation_format: OFormat[NewsCreation] = NewsCreation.format
  private implicit val edition_format: OFormat[NewsEdition]   = NewsEdition.format
}

class NewsClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials
    with EntityClient {

  import NewsClient._

  def byIds(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[News]]] = {
    ws.get[List[News]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, News]] = {
    ws.delete[News](s"$endPoint/$id", timeout)
  }

  def add(news_creation: NewsCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, News]] = {
    ws.put[News](s"$endPoint", timeout, Json.toJson(news_creation))
  }

  def edit(id: String, news_edition: NewsEdition, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, News]] = {
    ws.post[News](s"$endPoint/$id", timeout, Json.toJson(news_edition))
  }

  def search(criteria: NewsSearch, table_criteria: TableSearch, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, PaginatedSequence[News]]] = {
    ws.get[PaginatedSequence[News]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }
}
