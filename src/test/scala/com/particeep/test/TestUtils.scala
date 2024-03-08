package com.particeep.test

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

trait TestUtils {
  def await[A](f: Future[A], duration: Duration): A = Await.result(f, duration) // scalafix:ok
  def await[A](f: Future[A]): A                     = await(f, 10 seconds)
  def await[A](f: Seq[Future[A]]): Seq[A]           = await(Future.sequence(f)) // scalafix:ok
}
