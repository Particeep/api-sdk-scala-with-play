package com.particeep.api

import play.api.libs.json._

import scala.concurrent.{ ExecutionContext, Future }

import com.particeep.api.core._
import com.particeep.api.models._
import com.particeep.api.models.password_policy.{ PasswordPolicy, PasswordPolicyUpsert }

trait PasswordPolicyCapability {
  self: WSClient =>

  def passwordPolicy(credentials: ApiCredential): PasswordPolicyClient = {
    new PasswordPolicyClient(this, credentials)
  }
}

class PasswordPolicyClient(val ws: WSClient, val credentials: ApiCredential) extends WithWS {

  implicit val creds: ApiCredential  = credentials
  private[this] val endPoint: String = "/user/password-policy"

  def fetchPasswordPolicy(
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PasswordPolicy]] = {
    ws.get[PasswordPolicy](s"$endPoint", timeout, List())
  }

  def upsertPasswordPolicy(
    data:          PasswordPolicyUpsert,
    timeout:       Long = defaultTimeOut
  )(implicit exec: ExecutionContext): Future[Either[ErrorResult, PasswordPolicy]] = {
    ws.put[PasswordPolicy](s"$endPoint", timeout, Json.toJson(data))
  }
}
