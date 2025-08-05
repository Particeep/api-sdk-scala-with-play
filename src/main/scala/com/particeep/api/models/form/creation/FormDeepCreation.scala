package com.particeep.api.models.form.creation

import play.api.libs.json.{JsObject, Json, OFormat}

import com.particeep.api.models.enums.QuestionType

case class FormDeepCreation(
                             name:        Option[String],
                             description: Option[String],
                             tag:         Option[List[String]],
                             sections:    Option[Seq[SectionDeepCreation]],
                             custom:      Option[JsObject] = None
                           )

case class SectionDeepCreation(
                                id:          Option[String]                    = None,
                                name:        Option[Map[String, String]]       = None,
                                description: Option[Map[String, String]]       = None,
                                index:       Option[Int]                       = None,
                                questions:   Option[Seq[QuestionDeepCreation]] = None,
                                tag:         Option[String]                    = None,
                                custom:      Option[JsObject]                  = None
                              )

case class QuestionDeepCreation(
                                 id:                    Option[String]                       = None,
                                 label:                 Option[Map[String, String]]          = None,
                                 description:           Option[Map[String, String]]          = None,
                                 possibility_id_dep:    Option[String]                       = None,
                                 valid_possibility_ids: Option[String]                       = None,
                                 question_type:         Option[QuestionType]                 = Some(QuestionType.RADIO),
                                 required:              Boolean                              = false,
                                 pattern:               Option[String]                       = None,
                                 index:                 Option[Int]                          = None,
                                 possibilities:         Option[Seq[PossibilityDeepCreation]] = None,
                                 tag:                   Option[String]                       = None,
                                 document_filename:     Option[String]                       = None
                               )

case class PossibilityDeepCreation(
                                    id:     Option[String]              = None,
                                    label:  Option[Map[String, String]] = None,
                                    index:  Option[Int]                 = None,
                                    weight: Option[Int]                 = None
                                  )

object FormDeepCreation {
  implicit val possibility_deep_creation_format: OFormat[PossibilityDeepCreation] = Json.format[PossibilityDeepCreation]
  implicit val question_deep_creation_format: OFormat[QuestionDeepCreation] = Json.format[QuestionDeepCreation]
  implicit val section_deep_creation_format: OFormat[SectionDeepCreation] = Json.format[SectionDeepCreation]
  val format: OFormat[FormDeepCreation] = Json.format[FormDeepCreation]
}
