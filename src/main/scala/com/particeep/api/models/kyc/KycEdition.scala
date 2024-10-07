package com.particeep.api.models.kyc

import play.api.libs.json.{ Json, OFormat }

import com.particeep.api.models.enums.KycType
import com.particeep.api.models.enums.KycType.ID_CARD

case class KycEdition(
  doc_type: KycType     = ID_CARD,
  docs_ids: Seq[String] = Seq()
)

object KycEdition {
  val format: OFormat[KycEdition] = Json.format[KycEdition]
}

case class KycsEdition(
  owner_id:   String          = "",
  owner_type: String          = "",
  owner_ip:   Option[String]  = None,
  docs:       Seq[KycEdition] = Seq()
)

object KycsEdition {
  implicit val kyc_format: OFormat[KycEdition] = KycEdition.format
  val format: OFormat[KycsEdition]             = Json.format[KycsEdition]
}
