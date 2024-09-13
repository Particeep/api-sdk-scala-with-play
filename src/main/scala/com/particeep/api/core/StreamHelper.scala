package com.particeep.api.core

import org.apache.pekko.stream.scaladsl.RunnableGraph
import org.apache.pekko.stream.{ ActorAttributes, Supervision }

import scala.util.control.NonFatal

import org.slf4j.LoggerFactory

object StreamHelper {

  private[this] val logger = LoggerFactory.getLogger(this.getClass)

  def with_default_supervision[A](stream: RunnableGraph[A]): RunnableGraph[A] = {
    val decider: Supervision.Decider = {
      case NonFatal(e) =>
        logger.error(
          "Non fatal exception in stream, an element has been discarded. This is not a normal behaviour : must be investigate",
          e
        )
        Supervision.Resume
      case _           => Supervision.Stop
    }

    stream.withAttributes(ActorAttributes.supervisionStrategy(decider))
  }
}
