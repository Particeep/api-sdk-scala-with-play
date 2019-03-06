package com.particeep.api.models.enterprise

import play.api.libs.json.{JsObject, Json}

case class CustomEnterprise(
 created_by: Option[Creator] = None
) {
  def toJsObject(): JsObject = Json.toJson(this)(CustomEnterprise.customEnterpriseFormat).as[JsObject]
}

object CustomEnterprise {
  private[this] implicit val creatorFormat = Creator.format
  implicit val customEnterpriseFormat = Json.format[CustomEnterprise]
}
