package com.particeep.api.models.enums



object QuestionType {

  sealed abstract class QuestionType extends Product with Enum
  case object TEXT extends QuestionType
  case object LONGTEXT extends QuestionType
  case object DATE extends QuestionType
  case object RADIO extends QuestionType
  case object SELECT extends QuestionType
  case object CHECKBOX extends QuestionType
  case object DOCUMENT extends QuestionType
  case object LABEL extends QuestionType

  object QuestionType extends EnumHelper[QuestionType] {
    def values: Set[QuestionType] = Set(TEXT, LONGTEXT, DATE, RADIO, SELECT, CHECKBOX, DOCUMENT, LABEL)

    implicit def stringToQuestionType(value: String): QuestionType = get(value).getOrElse(TEXT)

    def isFreeType(question_type: QuestionType): Boolean = {
      Seq(TEXT, LONGTEXT).contains(question_type)
    }
  }
}
