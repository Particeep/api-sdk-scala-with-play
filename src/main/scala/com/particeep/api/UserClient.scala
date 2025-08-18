package com.particeep.api

import play.api.libs.json._
import play.shaded.ahc.org.asynchttpclient.request.body.multipart.StringPart

import java.io.File
import scala.concurrent.{ ExecutionContext, Future }

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString

import com.particeep.api.core._
import com.particeep.api.models._
import com.particeep.api.models.imports.{ ImportForm, ImportState }
import com.particeep.api.models.user._
import com.particeep.api.models.user.organization.{
  Organization,
  OrganizationOption,
  OrganizationUserLink,
  OrganizationUserLinkCreation,
  OrganizationUserLinkOption
}
import com.particeep.api.models.user.relative.{ NewRelative, NewRelativeCreation, NewRelativeEdition }
import com.particeep.api.utils.LangUtils

trait UserCapability {
  self: WSClient =>

  def user(credentials: ApiCredential): UserClient = new UserClient(this, credentials)
}

object UserClient {
  private val endPoint: String                                                              = "/user"
  private val endPoint_import: String                                                       = "/import"
  private implicit val format: OFormat[User]                                                = User.format
  private implicit val creation_format: OFormat[UserCreation]                               = UserCreation.format
  private implicit val edition_format: OFormat[UserEdition]                                 = UserEdition.format
  private implicit val data_format: OFormat[UserData]                                       = UserData.format
  private implicit val import_state_format: Format[ImportState]                             = ImportState.format
  private implicit val relative_creation_format: OFormat[RelativeCreation]                  = RelativeCreation.format
  private implicit val relative_format: OFormat[Relative]                                   = Relative.format
  private implicit val relative_option_format: OFormat[RelativeEdition]                     = RelativeEdition.format
  private implicit val new_relative_format: Format[NewRelative]                             = NewRelative.format
  private implicit val new_relative_edition_format: Format[NewRelativeEdition]              = NewRelativeEdition.format
  private implicit val new_relative_creation_format: Format[NewRelativeCreation]            = NewRelativeCreation.format
  private implicit val org_format: OFormat[Organization]                                    = Organization.format
  private implicit val org_option_format: OFormat[OrganizationOption]                       = OrganizationOption.format
  private implicit val org_user_link_format: OFormat[OrganizationUserLink]                  = OrganizationUserLink.format
  private implicit val org_user_link_creation_format: OFormat[OrganizationUserLinkCreation] =
    OrganizationUserLinkCreation.format
  private implicit val org_user_link_option_format: OFormat[OrganizationUserLinkOption]     =
    OrganizationUserLinkOption.format

  private case class ChangePassword(old_password: Option[String], new_password: String)
  private implicit val change_password_format: OFormat[ChangePassword] = Json.format[ChangePassword]
}

class UserClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  import UserClient._

  implicit val creds: ApiCredential = credentials

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
  ): Future[Either[ErrorResult, ImportState]] = {
    val bodyParts = List(
      new StringPart("tag", importForm.flatMap(_.tag).getOrElse("")),
      new StringPart("custom", importForm.flatMap(_.custom).map(Json.stringify).getOrElse(""))
    )

    ws.postFile[ImportState](s"$endPoint_import/user/csv", timeout, csv, "text/csv", bodyParts)
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

  def updateEmail(id: String, new_email: UserUpdateEmail, timeout: Long = defaultTimeOut)(implicit
    exec:             ExecutionContext
  ): Future[Either[ErrorResult, User]] = {
    ws.post[User](s"$endPoint/$id/email", timeout, Json.toJson(new_email))
  }

  def addNewRelative(
    user_id:           String,
    relative_creation: NewRelativeCreation,
    timeout:           Long = defaultTimeOut
  )(implicit exec:     ExecutionContext): Future[Either[ErrorResult, NewRelative]] = {
    ws.put[NewRelative](s"$endPoint/$user_id/new-relative", timeout, Json.toJson(relative_creation))
  }

  def updateNewRelative(
    user_id:          String,
    relative_id:      String,
    relative_edition: NewRelativeEdition,
    timeout:          Long = defaultTimeOut
  )(implicit
    exec:             ExecutionContext
  ): Future[Either[ErrorResult, NewRelative]] = {
    ws.post[NewRelative](s"$endPoint/$user_id/new-relative/$relative_id", timeout, Json.toJson(relative_edition))
  }

  def deleteNewRelative(user_id: String, relative_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                        ExecutionContext
  ): Future[Either[ErrorResult, NewRelative]] = {
    ws.delete[NewRelative](s"$endPoint/$user_id/new-relative/$relative_id", timeout)
  }

  def updateOrganization(
    organization_id: String,
    org_option:      OrganizationOption,
    timeout:         Long = defaultTimeOut
  )(implicit ec:     ExecutionContext): Future[Either[ErrorResult, Organization]] = {
    ws.post[Organization](s"$endPoint/organization/$organization_id", timeout, Json.toJson(org_option))
  }

  def addUserToOrganization(
    organization_id: String,
    user_id:         String,
    link_creation:   OrganizationUserLinkCreation,
    timeout:         Long = defaultTimeOut
  )(implicit ec:     ExecutionContext): Future[Either[ErrorResult, OrganizationUserLink]] = {
    ws.put[OrganizationUserLink](
      s"$endPoint/organization/$organization_id/user/$user_id",
      timeout,
      Json.toJson(link_creation)
    )
  }

  def updateOrganizationUserRole(
    organization_id: String,
    user_id:         String,
    link_update:     OrganizationUserLinkOption,
    timeout:         Long = defaultTimeOut
  )(implicit ec:     ExecutionContext): Future[Either[ErrorResult, OrganizationUserLink]] = {
    ws.post[OrganizationUserLink](
      s"$endPoint/organization/$organization_id/user/$user_id",
      timeout,
      Json.toJson(link_update)
    )
  }

  def deleteUserFromOrganization(organization_id: String, user_id: String, timeout: Long = defaultTimeOut)(implicit
    ec:                                           ExecutionContext
  ): Future[Either[ErrorResult, List[OrganizationUserLink]]] = {
    ws.delete[List[OrganizationUserLink]](s"$endPoint/organization/$organization_id/user/$user_id", timeout)
  }

  def deleteUsersFromOrganization(organization_id: String, ids: List[String], timeout: Long = defaultTimeOut)(implicit
    ec:                                            ExecutionContext
  ): Future[Either[ErrorResult, List[OrganizationUserLink]]] = {
    ws.delete[List[OrganizationUserLink]](
      s"$endPoint/organization/$organization_id/user",
      timeout,
      Json.obj("ids" -> ids)
    )
  }
}
