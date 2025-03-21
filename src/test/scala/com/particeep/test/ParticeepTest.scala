package com.particeep.test

import play.api.libs.json._

import java.time.{ OffsetDateTime, ZoneOffset }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

import org.apache.pekko.actor.ActorSystem
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import com.particeep.api.core.{ ApiClient, Formatter }
import com.particeep.api.models.ErrorResult
import com.particeep.api.models.user.User
import com.particeep.api.{ Info, InfoCapability }

class ParticeepTest extends AnyFlatSpec with Matchers with TestUtils {

  implicit val system: ActorSystem = ActorSystem("ParticeepTest")

  "the api client" should "load info" in {

    val ws = new ApiClient(ConfigTest.baseUrl, ConfigTest.version, Some(ConfigTest.credential)) with InfoCapability

    val rez_f: Future[Either[ErrorResult, Info]] = ws.info.info()

    val rez = await(rez_f, 10 seconds)
    rez.isRight shouldBe true

    val info = rez.getOrElse(Info(version = "0", debugEnable = true, metaEnable = true))
    info.version shouldBe "1"
  }

  "the api client" should "format date in ISO 8601" in {

    implicit val user_format: OFormat[User] = User.format

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
    val json                 = Formatter.OffsetDateTimeWrites.writes(date).toString()

    json shouldBe "\"2017-12-12T05:02:03Z\""
  }
}
