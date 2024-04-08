package com.particeep.api.core

import akka.stream.{ ActorAttributes, Supervision }
import akka.stream.scaladsl.RunnableGraph
import org.slf4j.LoggerFactory

import scala.util.control.NonFatal

object StreamHelper {

  private[this] val logger = LoggerFactory.getLogger(this.getClass)

  def with_default_supervision[A](stream: RunnableGraph[A]): RunnableGraph[A] = {
    val decider: Supervision.Decider = {
      case NonFatal(e) => {
        logger.error("Non fatal exception in stream, an element has been discarded. This is not a normal behaviour : must be investigate", e)
        Supervision.Resume
      }
      case _ => Supervision.Stop
    }

    stream.withAttributes(ActorAttributes.supervisionStrategy(decider))
  }
}
