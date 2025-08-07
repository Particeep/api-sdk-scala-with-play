package com.particeep.api

import play.api.libs.json.Json

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core.{ ApiCredential, WSClient, WithWS }
import com.particeep.api.models.control._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait ControlCapability {
  self: WSClient =>

  def control(credentials: ApiCredential): ControlClient = new ControlClient(this, credentials)
}

object ControlClient {
  private val endPoint: String = "/control"
}

class ControlClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import ControlClient._

  implicit val creds: ApiCredential = credentials

  def byIds(ids: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[ControlView]]] = {
    ws.get[List[ControlView]](s"$endPoint/", timeout, List("ids" -> ids))
  }

  def audit(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:       ExecutionContext
  ): Future[Either[ErrorResult, List[EventControl]]] = {
    ws.get[List[EventControl]](s"$endPoint/$id/audit", timeout)
  }

  def create(user_id: String, timeout: Long = defaultTimeOut, control_creation: ControlCreation)(implicit
    exec:             ExecutionContext
  ): Future[Either[ErrorResult, Control]] = {
    ws.put[Control](s"$endPoint/assign_to/$user_id", timeout, Json.toJson(control_creation))
  }

  def update(id: String, timeout: Long = defaultTimeOut, control_update: ControlUpdate)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Control]] = {
    ws.post[Control](s"$endPoint/$id", timeout, Json.toJson(control_update))
  }

  def updateBlocks(
    id:                    String,
    timeout:               Long = defaultTimeOut,
    control_blocks_update: ControlBlocksUpdate
  )(implicit exec:         ExecutionContext): Future[Either[ErrorResult, Control]] = {
    ws.post[Control](s"$endPoint/$id/block", timeout, Json.toJson(control_blocks_update))
  }

  def publish(id: String, ask_to_publish: AskToPublish, timeout: Long = defaultTimeOut)(implicit
    exec:         ExecutionContext
  ): Future[Either[ErrorResult, Control]] = {
    ws.post[Control](s"$endPoint/$id/publish", timeout, Json.toJson(ask_to_publish))
  }

  def search(
    criteria:       ControlViewSearchCriteria,
    table_criteria: TableSearch,
    timeout:        Long = defaultTimeOut
  )(implicit exec:  ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[ControlView]]] = {
    val params_query = LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    ws.get[PaginatedSequence[ControlView]](s"$endPoint/search", timeout, params_query)
  }
}
