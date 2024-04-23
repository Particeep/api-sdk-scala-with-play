package com.particeep.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.util.ByteString
import play.api.libs.json._
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models._
import com.particeep.api.models.imports.{ ImportForm, ImportResult }
import com.particeep.api.models.password_policy.{ PasswordPolicy, PasswordPolicyUpsert }
import com.particeep.api.models.user._
import com.particeep.api.utils.LangUtils

trait UserCapability {
  self: WSClient =>

  val user: UserClient = new UserClient(this)

  def user(credentials: ApiCredential): UserClient = new UserClient(this, Some(credentials))
}

object UserClient {
  private val endPoint: String                                             = "/user"
  private val endPoint_import: String                                      = "/import"
  private implicit val format: OFormat[User]                               = User.format
  private implicit val creation_format: OFormat[UserCreation]              = UserCreation.format
  private implicit val edition_format: OFormat[UserEdition]                = UserEdition.format
  private implicit val data_format: OFormat[UserData]                      = UserData.format
  private implicit val importResultReads: Format[ImportResult[User]]       = ImportResult.format[User]
  private implicit val relative_creation_format: OFormat[RelativeCreation] = RelativeCreation.format
  private implicit val relative_format: OFormat[Relative]                  = Relative.format
  private implicit val relative_option_format: OFormat[RelativeEdition]    = RelativeEdition.format

  private case class ChangePassword(old_password: Option[String], new_password: String)
  private implicit val change_password_format: OFormat[ChangePassword] = Json.format[ChangePassword]
}

class UserClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials
    with EntityClient {

  import UserClient._

  def byId(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:      ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.get[User](s"$endPoint/$id", timeout)
  }

  def byIds(ids: Seq[String], timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, List[User]]] = {
    ws.get[List[User]](s"$endPoint", timeout, List("ids" -> ids.mkString(",")))
  }

  def byEmail(email: String, timeout: Long = defaultTimeOut)(implicit
    exec:            ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.get[User](s"$endPoint/email/$email", timeout)
  }

  def search(
    criteria:            UserSearch,
    criteria_additional: UserSearchAdditional,
    table_criteria:      TableSearch,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, PaginatedSequence[UserData]]] = {
    ws.get[PaginatedSequence[UserData]](
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(
        criteria_additional
      ) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def companyBusinessNames(
    criteria:            UserSearch,
    criteria_additional: UserSearchAdditional,
    table_criteria:      TableSearch,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, List[String]]] = {
    ws.get[List[String]](
      s"$endPoint/company-business-name",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(
        criteria_additional
      ) ++ LangUtils.productToQueryString(table_criteria)
    )
  }

  def create(user_creation: UserCreation, timeout: Long = defaultTimeOut)(implicit
    exec:                   ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.put[User](s"$endPoint", timeout, Json.toJson(user_creation))
  }

  def update(id: String, user_edition: UserEdition, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.post[User](s"$endPoint/$id", timeout, Json.toJson(user_edition))
  }

  def authenticate(email: String, password: String, timeout: Long = defaultTimeOut)(implicit
    exec:                 ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.post[User](s"$endPoint/authenticate", timeout, Json.toJson(Map("email" -> email, "password" -> password)))
  }

  def changePassword(
    id:            String,
    old_password:  Option[String],
    new_password:  String,
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, User]] = {
    ws.post[User](s"$endPoint/$id/changePassword", timeout, Json.toJson(ChangePassword(old_password, new_password)))
  }

  def verifyAccount(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:               ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.post[User](s"$endPoint/verify/$id", timeout, Json.toJson(""))
  }

  def delete(id: String, timeout: Long = defaultTimeOut)(implicit
    exec:        ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.delete[User](s"$endPoint/$id", timeout)
  }

  def importFromCsv(csv: File, importForm: Option[ImportForm] = None, timeout: Long = defaultImportTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, ImportResult[User]]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )

    ws.postFile[ImportResult[User]](s"$endPoint_import/user/csv", timeout, csv, "text/csv", bodyParts)
  }

  def exportCsv(
    criteria:            UserSearch,
    criteria_additional: UserSearchAdditional,
    table_criteria:      TableSearch,
    timeout:             Long = defaultTimeOut
  )(implicit exec:       ExecutionContext): Future[Either[ErrorResult, Source[ByteString, NotUsed]]] = {
    ws.getStream(
      s"$endPoint/search",
      timeout,
      LangUtils.productToQueryString(criteria) ++ LangUtils.productToQueryString(
        criteria_additional
      ) ++ LangUtils.productToQueryString(table_criteria)
    )(exec, creds.withHeader("Content-Type", "application/csv"))
  }

  def addRelative(id: String, relative_option: RelativeCreation, timeout: Long = defaultTimeOut)(implicit
    exec:             ExecutionContext
  ): Future[Either[ErrorResult, Relative]] = {
    ws.put[Relative](s"$endPoint/$id/relative", timeout, Json.toJson(relative_option))
  }

  def deleteRelative(id: String, relative_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, Relative]] = {
    ws.delete[Relative](s"$endPoint/$id/relative/$relative_id", timeout)
  }

  def updateRelative(id: String, relative_edition: RelativeEdition, timeout: Long = defaultTimeOut)(implicit
    exec:                ExecutionContext
  ): Future[Either[ErrorResult, Relative]] = {
    ws.post[Relative](s"$endPoint/$id/relative", timeout, Json.toJson(relative_edition))
  }

  def fetchPasswordPolicy(
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PasswordPolicy]] = {
    ws.get[PasswordPolicy](s"$endPoint/password-policy", timeout, List())
  }

  def upsertPasswordPolicy(
    data:          PasswordPolicyUpsert,
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PasswordPolicy]] = {
    ws.put[PasswordPolicy](s"$endPoint/password-policy", timeout, Json.toJson(data))
  }
}
