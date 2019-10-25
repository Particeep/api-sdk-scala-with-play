package com.particeep.api

import play.api.libs.json._
import scala.concurrent.{ ExecutionContext, Future }
import com.particeep.api.models._
import com.particeep.api.core._
import com.particeep.api.models.group_user.{ GroupUser, GroupUserOption }

trait GroupUserCapability {
  self: WSClient =>

  val group_user: GroupUserClient = new GroupUserClient(this)

  def group_user(credentials: ApiCredential): GroupUserClient = new GroupUserClient(this, Some(credentials))
}

object GroupUserClient {
  private val endPoint: String = "/group-user"
  private implicit val format = GroupUserOption.format
  private implicit val group_user_format = GroupUser.format
}

class GroupUserClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials with EntityClient {
  import GroupUserClient._

  def create(group_user_creation: GroupUserOption, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, GroupUser]] = {
    ws.put[GroupUser](s"$endPoint", timeout, Json.toJson(group_user_creation))
  }

  def creates(group_users_creation: Seq[GroupUserOption], timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Seq[GroupUser]]] = {
    ws.put[Seq[GroupUser]](s"$endPoint/creates", timeout, Json.toJson(group_users_creation))
  }

  def byName(name: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Seq[GroupUser]]] = {
    ws.get[Seq[GroupUser]](s"$endPoint/$name", timeout)
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, GroupUser]] = {
    ws.delete[GroupUser](s"$endPoint/$id", timeout)
  }

  def deletes(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Seq[GroupUser]]] = {
    ws.delete[Seq[GroupUser]](s"$endPoint/$ids", timeout, Json.toJson(ids.mkString(",")))
  }
}
