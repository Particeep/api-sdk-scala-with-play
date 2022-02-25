package com.particeep.api.models.enums

sealed trait DocumentType extends Product with Enum

object DocumentType extends EnumHelper[DocumentType] {

  final case object ID extends DocumentType
  final case object IBAN extends DocumentType
  final case object CHEQUE extends DocumentType
  final case object TAX_SHEET extends DocumentType
  final case object PAY_SLIP extends DocumentType
  final case object ADDRESS_PROOF extends DocumentType
  final case object CREDIT_CARD extends DocumentType
  final case object PORTRAIT extends DocumentType
  final case object LEGAL_ENTITY extends DocumentType
  final case object CAR_REGISTRATION extends DocumentType
  final case object LIVENESS extends DocumentType
  final case object PASSPORT extends DocumentType
  final case object RESIDENCE_PERMIT extends DocumentType
  final case object DRIVING_LICENSE extends DocumentType
  final case object HEALTH_CARD extends DocumentType
  final case object VISA extends DocumentType
  final case object ADDRESS extends DocumentType
  final case object TAX extends DocumentType
  final case object PAY extends DocumentType
  final case object COMPANY extends DocumentType
  final case object COMPANY_REG_CERT extends DocumentType
  final case object UNKNOWN extends DocumentType

  val values = Set(ID, IBAN, CHEQUE, TAX_SHEET, PAY_SLIP, ADDRESS_PROOF, CREDIT_CARD,
    PORTRAIT, LEGAL_ENTITY, CAR_REGISTRATION, LIVENESS, PASSPORT, RESIDENCE_PERMIT,
    DRIVING_LICENSE, HEALTH_CARD, VISA, ADDRESS, TAX, PAY, COMPANY, COMPANY_REG_CERT, UNKNOWN)
}
