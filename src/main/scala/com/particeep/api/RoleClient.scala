package com.particeep.api

import play.api.libs.json.{ Format, Json, OFormat }
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.imports.{ ImportForm, ImportResult }
import com.particeep.api.models.role._
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait RoleCapability {
  self: WSClient =>

  def role(credentials: ApiCredential): RoleClient = new RoleClient(this, credentials)
}

object RoleClient {
  private val endPoint: String                                              = "/role"
  private val endPoint_import: String                                       = "/import"
  private implicit val role_format: OFormat[Role]                           = Role.format
  private implicit val roles_format: OFormat[Roles]                         = Roles.format
  private implicit val creation_format: OFormat[RoleCreation]               = RoleCreation.format
  private implicit val creations_format: OFormat[RolesCreation]             = RolesCreation.format
  private implicit val global_role_option_format: OFormat[GlobalRoleOption] = GlobalRoleOption.format
  private implicit val importResultReads: Format[ImportResult[Role]]        = ImportResult.format[Role]
}

class RoleClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import RoleClient._

  implicit val creds: ApiCredential = credentials

  def all(timeout: Long = defaultTimeOut)(implicit
    exec:          ExecutionContext
  ): Future[Either[ErrorResult, List[String]]] = {
    ws.get[List[String]](s"$endPoint/all", timeout)
  }

  def allByUser(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, Roles]] = {
    ws.get[Roles](s"$endPoint/all_by_user/$user_id", timeout)
  }

  def add(user_id: String, role: String, role_creation: RoleCreation, timeout: Long = defaultTimeOut)(implicit
    exec:          ExecutionContext
  ): Future[Either[ErrorResult, Roles]] = {
    ws.put[Roles](s"$endPoint/$user_id/add/${role.toLowerCase}", timeout, Json.toJson(role_creation))
  }

  def addRoles(roles_creation: List[RolesCreation], timeout: Long = defaultTimeOut)(implicit
    exec:                      ExecutionContext
  ): Future[Either[ErrorResult, List[Roles]]] = {
    ws.put[List[Roles]](s"$endPoint", timeout, Json.toJson(roles_creation))
  }

  private[this] case class TargetInfo(target_id: Option[String], target_type: Option[String])
  def remove(
    user_id:       String,
    role:          String,
    target_id:     Option[String] = None,
    target_type:   Option[String] = None,
    timeout:       Long           = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, Roles]] = {
    ws.delete[Roles](
      s"$endPoint/$user_id/remove/${role.toLowerCase}",
      timeout,
      Json.toJson(""),
      LangUtils.productToQueryString(TargetInfo(target_id, target_type))
    )
  }

  def remove_multiple(
    user_id:       String,
    roles:         Seq[String],
    target_id:     Option[String] = None,
    target_type:   Option[String] = None,
    timeout:       Long           = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, Roles]] = {
    ws.delete[Roles](
      s"$endPoint/$user_id/remove_roles",
      timeout,
      Json.toJson(roles),
      LangUtils.productToQueryString(TargetInfo(target_id, target_type))
    )
  }

  def hasRole(user_id: String, role: String)(implicit exec: ExecutionContext): Future[Either[ErrorResult, Boolean]] = {
    allByUser(user_id).map(result => result.map(roles => roles.roles.map(_.role_name).contains(role)))
  }

  def search(criteria: RoleSearch, table_criteria: TableSearch, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, PaginatedSequence[Roles]]] = {
    ws.get[PaginatedSequence[Roles]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def update_roles(ids: String, global_role_option: GlobalRoleOption, timeout: Long = defaultTimeOut)(implicit
    exec:               ExecutionContext
  ): Future[Either[ErrorResult, List[Roles]]] = {
    ws.post[List[Roles]](s"$endPoint/update/$ids", timeout, Json.toJson(global_role_option))
  }

  def importFromCsv(csv: File, importForm: Option[ImportForm] = None, timeout: Long = defaultImportTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, ImportResult[Role]]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )
    ws.postFile[ImportResult[Role]](s"$endPoint_import/role/csv", timeout, csv, "text/csv", bodyParts)
  }
}
