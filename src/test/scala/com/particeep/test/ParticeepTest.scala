package com.particeep.test

import java.time.{ ZoneOffset, OffsetDateTime }

import akka.actor.ActorSystem
import akka.stream.Materializer

import scala.language.postfixOps
import com.particeep.api.core.ApiClient
import com.particeep.api.core.Formatter
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.user.User
import com.particeep.api.Info
import com.particeep.api.InfoCapability
import javax.inject.{ Inject, Singleton }
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import play.api.libs.json.Json

import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ParticeepTest @Inject() (implicit system: ActorSystem, val materializer: Materializer) extends AnyFlatSpec with Matchers {

  "the api client" should "load info" in {

    val ws = new ApiClient(ConfigTest.baseUrl, ConfigTest.version, Some(ConfigTest.credential)) with InfoCapability

    val rez_f: Future[Either[ErrorResult, Info]] = ws.info.info()

    val rez = Await.result(rez_f, 10 seconds)
    rez.isRight shouldBe true

    val info = rez.getOrElse(Info(version = "0", debugEnable = true, metaEnable = true))
    info.version shouldBe "1"
  }

  "the api client" should "format date in ISO 8601" in {

    implicit val user_format = User.format

    val date = OffsetDateTime.now(ZoneOffset.UTC)
      .withYear(1980)
      .withMonth(1)
      .withDayOfMonth(2)
      .withHour(3)
      .withMinute(20)
      .withSecond(12)
      .withNano(0)

    val user = User(id = "1234", email = "toto@gmail.com", birthday = Some(date))
    val json = Json.toJson(user)

    val result = Json.parse("""
        |{
        | "id": "1234",
        | "email": "toto@gmail.com",
        | "birthday": "1980-01-02T03:20:12Z"
        |}
        """.stripMargin)

    Json.prettyPrint(json) shouldBe Json.prettyPrint(result)
  }

  "the api client" should "format a date in ISO with UTC Zone" in {
    val date: OffsetDateTime = OffsetDateTime.of(2017, 12, 12, 8, 2, 3, 0, ZoneOffset.of("+03:00"))
    val json = Formatter.OffsetDateTimeWrites.writes(date).toString()

    json shouldBe "\"2017-12-12T05:02:03Z\""
  }
}
