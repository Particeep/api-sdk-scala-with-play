package com.particeep.api.models.fundraise.loan

import play.api.libs.json.{Json, OFormat, Writes}

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter

case class RepaymentVectorWithDate(
    date:                   OffsetDateTime,
    capital:                BigDecimal,
    interest:               BigDecimal,
    taxes:                  BigDecimal,
    capital_remains_to_pay: BigDecimal
)

case class RepaymentInfoVector(
    repayments: Option[List[RepaymentVectorWithDate]] = None
)

object RepaymentInfoVector {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val repayment_vector_date_format: OFormat[RepaymentVectorWithDate] = Json.format[RepaymentVectorWithDate]
  val format = Json.format[RepaymentInfoVector]
}
