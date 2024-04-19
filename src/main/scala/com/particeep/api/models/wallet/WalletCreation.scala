package com.particeep.api.models.wallet

import play.api.libs.json.{Format, JsObject, Json, OFormat, Writes}

import java.time.{OffsetDateTime, ZoneOffset}
import com.particeep.api.core.Formatter
import com.particeep.api.models.Address
import com.particeep.api.models.enums.WalletType.{NATURAL, WalletType}

case class WalletCreation(
  owner_id:            String           = "",
  owner_type:          String           = "",
  wallet_type:         WalletType       = NATURAL,
  address:             Address          = Address(),
  owner_ip:            String           = "",
  email:               String           = "",
  first_name:          String           = "",
  last_name:           String           = "",
  gender:              String           = "",
  birthday:            OffsetDateTime   = OffsetDateTime.now(ZoneOffset.UTC),
  is_payer:            Option[Boolean]  = None,
  nationality:         Option[String]   = None,
  countryOfResidence:  Option[String]   = None,
  occupation:          Option[String]   = None,
  incomeRange:         Option[Int]      = None,
  businessName:        Option[String]   = None,
  businessDescription: Option[String]   = None,
  businessWebsite:     Option[String]   = None,
  businessAddress:     Option[Address]  = None,
  tag:                 Option[String]   = None,
  custom:              Option[JsObject] = None
)

object WalletCreation {
  implicit val date_format: Writes[OffsetDateTime] = Formatter.OffsetDateTimeWrites
  implicit val address_format: Format[Address]     = Address.format
  val format: OFormat[WalletCreation] = Json.format[WalletCreation]
}
