package com.particeep.api.core

trait WithWS {
  def ws: WSClient
  val defaultTimeOut: Long       = ws.defaultTimeOut
  val defaultImportTimeOut: Long = ws.defaultImportTimeOut
}
