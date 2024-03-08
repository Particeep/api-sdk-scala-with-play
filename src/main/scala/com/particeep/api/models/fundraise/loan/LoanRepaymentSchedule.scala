package com.particeep.api.models.fundraise.loan

import java.time.OffsetDateTime

import com.particeep.api.core.Formatter
import com.particeep.api.models.user.User
import play.api.libs.json._

case class LoanRepaymentSchedule(
    id:                     String,
    created_at:             OffsetDateTime,
    user_id:                String,
    enterprise_id:          String,
    fundraise_id:           String,
    payment_date:           OffsetDateTime,
    capital:                Int,
    capital_remains_to_pay: Int,
    interest:               Int,
    tax:                    Int,
    fees:                   Int,
    transaction_id:         String,
    is_amount_paid:         Boolean,
    is_tax_paid:            Boolean,
    is_offline:             Boolean,
    tag:                    Option[String]   = None,
    custom:                 Option[JsObject] = None
)

object LoanRepaymentSchedule {
  implicit val date_format: Writes[OffsetDateTime]      = Formatter.OffsetDateTimeWrites
  implicit val format: OFormat[LoanRepaymentSchedule]   = Json.format[LoanRepaymentSchedule]
  private[this] implicit val user_format: OFormat[User] = User.format

  val loan_repayment_schedule_and_user_format: Format[(LoanRepaymentSchedule, User)] = new Format[(LoanRepaymentSchedule, User)] {
    def reads(json: JsValue): JsResult[(LoanRepaymentSchedule, User)] = JsSuccess((
      json.as[LoanRepaymentSchedule],
      (json \ "user").as[User]
    ))
    def writes(repaymentWithDateAndUser: (LoanRepaymentSchedule, User)): JsValue =
      Json.toJson(repaymentWithDateAndUser._1).as[JsObject] + ("user" -> Json.toJson(repaymentWithDateAndUser._2))
  }
}
