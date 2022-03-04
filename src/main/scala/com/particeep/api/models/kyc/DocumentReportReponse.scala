package com.particeep.api.models.kyc

import play.api.libs.json.Json

case class DocumentReportResponse(
    info:    Option[DocumentReportInfoResponse],
    persons: Option[List[DocumentReportPerson]]
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
    firstNames: Option[List[ReportDataItem]],
    lastName:   Option[ReportDataItem]
)

case class ReportDataItem(
    value: Option[String]
)

object DocumentReportResponse {
  implicit val rdi_format = Json.format[ReportDataItem]
  implicit val rid_format = Json.format[ReportIdentityData]
  implicit val rfd_format = Json.format[ReportFinanceData]
  implicit val drp_format = Json.format[DocumentReportPerson]
  implicit val drir_format = Json.format[DocumentReportInfoResponse]
  val format = Json.format[DocumentReportResponse]
}
