package com.particeep.api

import play.api.libs.json._
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models.enterprise._
import com.particeep.api.models.imports.{ ImportForm, ImportResult }
import com.particeep.api.models.{ ErrorResult, PaginatedSequence, TableSearch }
import com.particeep.api.utils.LangUtils

trait EnterpriseCapability {
  self: WSClient =>

  val enterprise: EnterpriseClient                             = new EnterpriseClient(this)
  def enterprise(credentials: ApiCredential): EnterpriseClient = new EnterpriseClient(this, Some(credentials))
}

object EnterpriseClient {
  private val endPoint: String                                                                          = "/enterprise"
  private val endPoint_import: String                                                                   = "/import"
  private implicit val format: OFormat[Enterprise]                                                      = Enterprise.format
  private implicit val creation_format: OFormat[EnterpriseCreation]                                     = EnterpriseCreation.format
  private implicit val edition_format: OFormat[EnterpriseEdition]                                       = EnterpriseEdition.format
  private implicit val manager_link_format: OFormat[ManagerLink]                                        = ManagerLink.format
  private implicit val manager_creation_format: OFormat[ManagerCreation]                                = ManagerCreation.format
  private implicit val nb_enterprises_by_activity_domain_format: OFormat[NbEnterprisesByActivityDomain] =
    NbEnterprisesByActivityDomain.format
  private implicit val importResultReads: Format[ImportResult[Enterprise]]                              = ImportResult.format[Enterprise]

}

class EnterpriseClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS
    with WithCredentials with EntityClient {

  import EnterpriseClient._

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, Enterprise]] = {
    ws.get[Enterprise](s"$endPoint/$id", timeout)
  }

  def byIds(ids: List[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[Enterprise]]] = {
    ws.get[List[Enterprise]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def byUserId(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:               ExecutionContext
  ): Future[Either[ErrorResult, List[Enterprise]]] = {
    ws.get[List[Enterprise]](s"$endPoint/user/$user_id", timeout)
  }

  def create(enterprise_creation: EnterpriseCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                         ExecutionContext
  ): Future[Either[ErrorResult, Enterprise]] = {
    ws.put[Enterprise](s"$endPoint", timeout, Json.toJson(enterprise_creation))
  }

  def update(id: String, enterprise_edition: EnterpriseEdition, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Enterprise]] = {
    ws.post[Enterprise](s"$endPoint/$id", timeout, Json.toJson(enterprise_edition))
  }

  def search(criteria: EnterpriseSearch, table_criteria: TableSearch, timeout: Long = defaultTimeOut)(implicit
    exec:              ExecutionContext
  ): Future[Either[ErrorResult, PaginatedSequence[Enterprise]]] = {
    ws.get[PaginatedSequence[Enterprise]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, Enterprise]] = {
    ws.delete[Enterprise](s"$endPoint/$id", timeout)
  }

  def addManager(id: String, manager_creation: ManagerCreation, timeout: Long = defaultTimeOut)(implicit
    exec:            ExecutionContext
  ): Future[Either[ErrorResult, ManagerLink]] = {
    ws.put[ManagerLink](s"$endPoint/$id/manager", timeout, Json.toJson(manager_creation))
  }

  def getManagers(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:             ExecutionContext
  ): Future[Either[ErrorResult, List[ManagerLink]]] = {
    ws.get[List[ManagerLink]](s"$endPoint/$id/manager", timeout)
  }

  def deleteManager(id: String, manager_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:               ExecutionContext
  ): Future[Either[ErrorResult, ManagerLink]] = {
    ws.delete[ManagerLink](s"$endPoint/$id/manager/$manager_id", timeout)
  }

  def nbEnterprisesByActivityDomain(timeout: Long = defaultTimeOut)(implicit
    exec:                                    ExecutionContext
  ): Future[Either[ErrorResult, Seq[NbEnterprisesByActivityDomain]]] = {
    ws.get[Seq[NbEnterprisesByActivityDomain]](s"$endPoint/info/activity/domain", timeout)
  }

  def importFromCsv(file: File, importForm: Option[ImportForm] = None, timeout: Long = defaultImportTimeOut)(implicit
    exec:                 ExecutionContext
  ): Future[Either[ErrorResult, ImportResult[Enterprise]]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )

    ws.postFile[ImportResult[Enterprise]](s"$endPoint_import/enterprise/csv", timeout, file, "text/csv", bodyParts)
  }
}
