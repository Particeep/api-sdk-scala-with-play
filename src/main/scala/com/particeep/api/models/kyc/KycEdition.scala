package com.particeep.api.models.kyc

import com.particeep.api.models.enums.KycType.{ ID_CARD, KycType }
import play.api.libs.json.{ Json, OFormat }

import java.util.UUID

case class KycEdition(
    doc_type: KycType   = ID_CARD,
    docs_ids: Seq[UUID] = Seq()
)

object KycEdition {
  val format = Json.format[KycEdition]
}

case class KycsEdition(
    owner_id:   String          = "",
    owner_type: String          = "",
    owner_ip:   Option[String]  = None,
    docs:       Seq[KycEdition] = Seq()
)

object KycsEdition {
  implicit val kyc_format: OFormat[KycEdition] = KycEdition.format
  val format = Json.format[KycsEdition]
}
