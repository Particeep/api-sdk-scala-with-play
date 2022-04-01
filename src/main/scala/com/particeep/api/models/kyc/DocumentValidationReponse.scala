package com.particeep.api.models.kyc

import com.particeep.api.models.enums.{ EnumHelper, Enum }
import play.api.libs.json.Json

case class DocumentValidationResponse(
    lastReport: Option[DocumentReportResponse]
)

case class DocumentReportResponse(
    checks:  List[CheckResponse],
    info:    Option[DocumentReportInfoResponse],
    persons: Option[List[DocumentReportPerson]]
)

case class CheckResponse(
    identifier: String,
    status:     CheckStatus,
    subChecks:  Option[List[CheckResponse]]
)

case class DocumentReportInfoResponse(
    expirationDay:   Option[ReportDataItem],
    expirationMonth: Option[ReportDataItem],
    expirationYear:  Option[ReportDataItem]
)

case class DocumentReportPerson(
    financeData:  Option[ReportFinanceData],
    identityData: Option[ReportIdentityData]
)

case class ReportFinanceData(
    iban: Option[ReportDataItem],
    bic:  Option[ReportDataItem]
)

case class ReportIdentityData(
    birthDay:   Option[ReportDataItem],
    birthMonth: Option[ReportDataItem],
    birthYear:  Option[ReportDataItem],
    firstNames: Option[ReportDataListItem],
    lastName:   Option[ReportDataItem]
)

case class ReportDataItem(
    value: Option[String]
)

case class ReportDataListItem(
    values: Option[Seq[String]]
)

sealed trait CheckStatus extends Product with Enum

object CheckStatus extends EnumHelper[CheckStatus] {

  final case object NONE extends CheckStatus
  final case object OK extends CheckStatus
  final case object WARN extends CheckStatus
  final case object ERROR extends CheckStatus
  final case object OBSOLETE extends CheckStatus

  val values = Set(NONE, OK, WARN, ERROR, OBSOLETE)
}

object DocumentValidationResponse {
  implicit val cr_format = Json.format[CheckResponse]
  implicit val rdi_format = Json.format[ReportDataItem]
  implicit val rdli_format = Json.format[ReportDataListItem]
  implicit val rid_format = Json.format[ReportIdentityData]
  implicit val rfd_format = Json.format[ReportFinanceData]
  implicit val drp_format = Json.format[DocumentReportPerson]
  implicit val drir_format = Json.format[DocumentReportInfoResponse]
  implicit val drr_format = Json.format[DocumentReportResponse]
  val format = Json.format[DocumentValidationResponse]
}
