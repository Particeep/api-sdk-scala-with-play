package com.particeep.test

import akka.actor.ActorSystem
import akka.stream.Materializer

import scala.language.postfixOps
import com.particeep.api.models.user.User
import com.particeep.api.core.ApiClient
import com.particeep.api.models._
import com.particeep.api.UserCapability
import com.particeep.api.ParticeepApi
import javax.inject.{ Inject, Singleton }
import play.api.libs.ws.StandaloneWSResponse
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import play.api.libs.json.Reads

import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class UserTest @Inject() (implicit system: ActorSystem, val materializer: Materializer) extends AnyFlatSpec with Matchers {

  "the api client" should "load user by id with correct date format" in {

    val user_id = "bf5788e8-9756-4d18-8b0f-100d7fba17a2"
    val ws = new ApiClient(ConfigTest.baseUrl, ConfigTest.version, Some(ConfigTest.credential)) with UserCapability
    val rez_f: Future[Either[ErrorResult, User]] = ws.user.byId(user_id)

    val rez = Await.result(rez_f, 10 seconds)
    rez.isRight shouldBe true

    val user = rez.getOrElse(User(id = "1234", email = "toto@gmail.com"))
    user.id shouldBe user_id

    val created_at = user.created_at.map(_.toString).getOrElse("")
    created_at shouldBe "2015-08-10T00:00Z"
  }

  "the api client" should "load user by id with direct credentials" in {

    val user_id = "bf5788e8-9756-4d18-8b0f-100d7fba17a2"
    val ws = new ApiClient(ConfigTest.baseUrl, ConfigTest.version, Some(ConfigTest.credential)) with UserCapability
    val rez_f: Future[Either[ErrorResult, User]] = ws.user.byId(user_id)

    val rez = Await.result(rez_f, 10 seconds)
    rez.isRight shouldBe true

    val user = rez.getOrElse(User(id = "1234", email = "toto@gmail.com"))
    user.id shouldBe user_id
  }

  "the api client" should "load user by id with custom credentials" in {

    val user_id = "bf5788e8-9756-4d18-8b0f-100d7fba17a2"
    val ws = new ApiClient(ConfigTest.baseUrl, ConfigTest.version) with UserCapability
    val credentials = ConfigTest.credential
    val rez_f: Future[Either[ErrorResult, User]] = ws.user(credentials).byId(user_id)

    val rez = Await.result(rez_f, 10 seconds)
    rez.isRight shouldBe true

    val user = rez.getOrElse(User(id = "1234", email = "toto@gmail.com"))
    user.id shouldBe user_id
  }

  "the api client" should "load user by id with helper" in {

    val user_id = "bf5788e8-9756-4d18-8b0f-100d7fba17a2"
    val ws = ParticeepApi.test(ConfigTest.credential.apiKey, ConfigTest.credential.apiSecret)
    val rez_f: Future[Either[ErrorResult, User]] = ws.user.byId(user_id)

    val rez = Await.result(rez_f, 10 seconds)
    rez.isRight shouldBe true

    val user = rez.getOrElse(User(id = "1234", email = "toto@gmail.com"))
    user.id shouldBe user_id
  }

  "the api client" should "load user by id with helper and overload credentials" in {

    val user_id = "bf5788e8-9756-4d18-8b0f-100d7fba17a2"
    val ws = ParticeepApi.test(ConfigTest.credential.apiKey, ConfigTest.credential.apiSecret)
    val credentials = ConfigTest.credential

    val rez_f: Future[Either[ErrorResult, User]] = ws.user(credentials).byId(user_id)

    val rez = Await.result(rez_f, 10 seconds)
    rez.isRight shouldBe true

    val user = rez.getOrElse(User(id = "1234", email = "toto@gmail.com"))
    user.id shouldBe user_id
  }

  "the api client" should "load user by id with helper and overload credentials with custom header" in {

    val user_id = "bf5788e8-9756-4d18-8b0f-100d7fba17a2"
    val ws = new ApiClient("https://test-api.particeep.com", "1", Some(ConfigTest.credential)) with UserCapability {
      override def parse[A](response: StandaloneWSResponse)(implicit json_reads: Reads[A]): Either[ErrorResult, A] = {
        val msg = response
          .headers
          .view.filterKeys(k => k == "Info-End-User")
          .headOption
          .map(v => s"${v._1} -> ${v._2}")
          .getOrElse("")

        Left(Errors(true, List(Error(msg, msg))))
      }
    }

    val credentials = ConfigTest.credential.withHeader("Info-End-User", "1234")

    val rez_f: Future[Either[ErrorResult, User]] = ws.user(credentials).byId(user_id)

    val rez = Await.result(rez_f, 10 seconds)
    rez.isLeft shouldBe true

    val error_msg = rez.left.getOrElse(Errors).asInstanceOf[Errors]
    val maybe_header = error_msg.errors
      .flatMap(_.message.split(","))
      .filter(_.contains("Info-End-User"))
      .headOption

    maybe_header shouldBe Some("Info-End-User -> Buffer(1234)")
  }
}
