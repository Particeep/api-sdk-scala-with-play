package com.particeep.api

import play.api.libs.json.{ Json, OFormat }

import scala.concurrent.{ ExecutionContext, Future }

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString

import com.particeep.api.core._
import com.particeep.api.models.partner.{ PartnerCompany, PartnerCompanyCreation, PartnerCompanyEdition, _ }
import com.particeep.api.models.user.{ UserSearch, UserSearchAdditional }
import com.particeep.api.models.{ ErrorResult, TableSearch }
import com.particeep.api.utils.LangUtils

trait PartnerCapability {
  self: WSClient =>

  val partner: PartnerClient = new PartnerClient(this)

  def partner(credentials: ApiCredential): PartnerClient = new PartnerClient(this, Some(credentials))
}

object PartnerClient {
  private val endPoint: String                                                                      = "/partner"
  private implicit val partner_fees_format: OFormat[PartnerFees]                                    = PartnerFees.format
  private implicit val partner_fees_creation_format: OFormat[PartnerFeesCreation]                   = PartnerFeesCreation.format
  private implicit val partner_fees_edition_format: OFormat[PartnerFeesEdition]                     = PartnerFeesEdition.format
  private implicit val partner_fees_on_target_format: OFormat[PartnerFeesOnTarget]                  = PartnerFeesOnTarget.format
  private implicit val partner_fees_on_target_creation_format: OFormat[PartnerFeesOnTargetCreation] =
    PartnerFeesOnTargetCreation.format
  private implicit val partner_fees_on_target_edition_format: OFormat[PartnerFeesOnTargetEdition]   =
    PartnerFeesOnTargetEdition.format
  private implicit val partner_company_format: OFormat[PartnerCompany]                              = PartnerCompany.format
  private implicit val partner_company_creation_format: OFormat[PartnerCompanyCreation]             = PartnerCompanyCreation.format
  private implicit val partner_company_edition_format: OFormat[PartnerCompanyEdition]               = PartnerCompanyEdition.format
}

class PartnerClient(val ws: WSClient, val credentials: Option[ApiCredential] = None) extends WithWS with WithCredentials
    with EntityClient {

  import PartnerClient._

  def createDefaultPartnerFees(
    user_id:               String,
    partner_fees_creation: PartnerFeesCreation,
    timeout:               Long = defaultTimeOut
  )(implicit exec:         ExecutionContext): Future[Either[ErrorResult, PartnerFees]] = {
    ws.put[PartnerFees](s"$endPoint/fees/$user_id/default", timeout, Json.toJson(partner_fees_creation))
  }

  def getDefaultPartnerFeesByUserId(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                                    ExecutionContext
  ): Future[Either[ErrorResult, PartnerFees]] = {
    ws.get[PartnerFees](s"$endPoint/fees/$user_id/default", timeout)
  }

  def updateDefaultPartnerFees(
    user_id:              String,
    partner_fees_edition: PartnerFeesEdition,
    timeout:              Long = defaultTimeOut
  )(implicit exec:        ExecutionContext): Future[Either[ErrorResult, PartnerFees]] = {
    ws.post[PartnerFees](s"$endPoint/fees/$user_id/default", timeout, Json.toJson(partner_fees_edition))
  }

  def deleteDefaultPartnerFees(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                               ExecutionContext
  ): Future[Either[ErrorResult, PartnerFees]] = {
    ws.delete[PartnerFees](s"$endPoint/fees/$user_id/default", timeout)
  }

  def createPartnerFees(
    user_id:                         String,
    target_id:                       String,
    target_type:                     String,
    partner_fees_on_target_creation: PartnerFeesOnTargetCreation,
    timeout:                         Long = defaultTimeOut
  )(implicit exec:                   ExecutionContext): Future[Either[ErrorResult, PartnerFeesOnTarget]] = {
    ws.put[PartnerFeesOnTarget](
      s"$endPoint/fees/$user_id/$target_id/$target_type",
      timeout,
      Json.toJson(partner_fees_on_target_creation)
    )
  }

  def getPartnerFees(user_id: String, target_id: String, target_type: String, timeout: Long = defaultTimeOut)(implicit
    exec:                     ExecutionContext
  ): Future[Either[ErrorResult, PartnerFeesOnTarget]] = {
    ws.get[PartnerFeesOnTarget](s"$endPoint/fees/$user_id/$target_id/$target_type", timeout)
  }

  def updatePartnerFees(
    user_id:                        String,
    target_id:                      String,
    target_type:                    String,
    partner_fees_on_target_edition: PartnerFeesOnTargetEdition,
    timeout:                        Long = defaultTimeOut
  )(implicit exec:                  ExecutionContext): Future[Either[ErrorResult, PartnerFeesOnTarget]] = {
    ws.post[PartnerFeesOnTarget](
      s"$endPoint/fees/$user_id/$target_id/$target_type",
      timeout,
      Json.toJson(partner_fees_on_target_edition)
    )
  }

  def deletePartnerFees(
    user_id:       String,
    target_id:     String,
    target_type:   String,
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PartnerFeesOnTarget]] = {
    ws.delete[PartnerFeesOnTarget](s"$endPoint/fees/$user_id/$target_id/$target_type", timeout)
  }

  def createPartnerCompany(
    user_id:                  String,
    partner_company_creation: PartnerCompanyCreation,
    timeout:                  Long = defaultTimeOut
  )(implicit exec:            ExecutionContext): Future[Either[ErrorResult, PartnerCompany]] = {
    ws.put[PartnerCompany](s"$endPoint/company/$user_id", timeout, Json.toJson(partner_company_creation))
  }

  def getPartnerCompanyByUserId(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                                ExecutionContext
  ): Future[Either[ErrorResult, PartnerCompany]] = {
    ws.get[PartnerCompany](s"$endPoint/company/$user_id", timeout)
  }

  def getPartnerCompanysByUserIds(user_ids: List[String], timeout: Long = defaultTimeOut)(implicit
    exec:                                   ExecutionContext
  ): Future[Either[ErrorResult, List[PartnerCompany]]] = {
    ws.get[List[PartnerCompany]](s"$endPoint/company", timeout, List("user_ids" -> user_ids.mkString(",")))
  }

  def updatePartnerCompany(
    user_id:                 String,
    partner_company_edition: PartnerCompanyEdition,
    timeout:                 Long = defaultTimeOut
  )(implicit exec:           ExecutionContext): Future[Either[ErrorResult, PartnerCompany]] = {
    ws.post[PartnerCompany](s"$endPoint/company/$user_id", timeout, Json.toJson(partner_company_edition))
  }

  def deletePartnerCompany(user_id: String, timeout: Long = defaultTimeOut)(implicit
    exec:                           ExecutionContext
  ): Future[Either[ErrorResult, PartnerCompany]] = {
    ws.delete[PartnerCompany](s"$endPoint/company/$user_id", timeout)
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
}
