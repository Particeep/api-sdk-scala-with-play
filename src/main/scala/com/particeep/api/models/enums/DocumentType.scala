package com.particeep.api.models.enums

sealed trait DocumentType extends Product with Enum

object DocumentType extends EnumHelper[DocumentType] {

  case object ID               extends DocumentType
  case object IBAN             extends DocumentType
  case object CHEQUE           extends DocumentType
  case object TAX_SHEET        extends DocumentType
  case object PAY_SLIP         extends DocumentType
  case object ADDRESS_PROOF    extends DocumentType
  case object CREDIT_CARD      extends DocumentType
  case object PORTRAIT         extends DocumentType
  case object LEGAL_ENTITY     extends DocumentType
  case object CAR_REGISTRATION extends DocumentType
  case object LIVENESS         extends DocumentType
  case object PASSPORT         extends DocumentType
  case object RESIDENCE_PERMIT extends DocumentType
  case object DRIVING_LICENSE  extends DocumentType
  case object HEALTH_CARD      extends DocumentType
  case object VISA             extends DocumentType
  case object ADDRESS          extends DocumentType
  case object TAX              extends DocumentType
  case object PAY              extends DocumentType
  case object COMPANY          extends DocumentType
  case object COMPANY_REG_CERT extends DocumentType
  case object UNKNOWN          extends DocumentType

  val values: Set[DocumentType] = Set(
    ID,
    IBAN,
    CHEQUE,
    TAX_SHEET,
    PAY_SLIP,
    ADDRESS_PROOF,
    CREDIT_CARD,
    PORTRAIT,
    LEGAL_ENTITY,
    CAR_REGISTRATION,
    LIVENESS,
    PASSPORT,
    RESIDENCE_PERMIT,
    DRIVING_LICENSE,
    HEALTH_CARD,
    VISA,
    ADDRESS,
    TAX,
    PAY,
    COMPANY,
    COMPANY_REG_CERT,
    UNKNOWN
  )
}
