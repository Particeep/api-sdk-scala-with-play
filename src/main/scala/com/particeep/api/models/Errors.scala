package com.particeep.api.models

import play.api.libs.json.{JsValue, Json, OFormat}

case class Error(technicalCode: String, message: String, code: Option[String] = None, stack: Option[String] = None)

trait ErrorResult
case class Errors(hasError: Boolean, errors: List[Error]) extends ErrorResult
case class ParsingError(hasError: Boolean, errors: List[JsValue]) extends ErrorResult

object Errors {
  implicit val error_format: OFormat[Error] = Json.format[Error]
  implicit val errors_format: OFormat[Errors] = Json.format[Errors]
  implicit val parsing_error_format: OFormat[ParsingError] = Json.format[ParsingError]
}
