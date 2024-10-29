package com.particeep.api.models.control

import play.api.libs.json.{Json, OFormat}

case class AskToPublish(
                         author_email: String
                       )

object AskToPublish {
  implicit val ask_to_publish_format: OFormat[AskToPublish] = Json.format[AskToPublish]
}
