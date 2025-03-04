package com.particeep.api.models.enums

sealed trait QuestionType extends Product with Enum

object QuestionType extends EnumHelper[QuestionType] {
  case object TEXT     extends QuestionType
  case object LONGTEXT extends QuestionType
  case object DATE     extends QuestionType
  case object RADIO    extends QuestionType
  case object SELECT   extends QuestionType
  case object CHECKBOX extends QuestionType
  case object DOCUMENT extends QuestionType
  case object LABEL    extends QuestionType

  def values: Set[QuestionType] = Set(TEXT, LONGTEXT, DATE, RADIO, SELECT, CHECKBOX, DOCUMENT, LABEL)

  implicit def stringToQuestionType(value: String): QuestionType = get(value).getOrElse(TEXT)
}
