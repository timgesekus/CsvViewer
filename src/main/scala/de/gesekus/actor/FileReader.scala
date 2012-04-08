package de.gesekus.actor
import akka.actor.Actor
import akka.event.Logging
import java.io.FileNotFoundException
import akka.dispatch.Future
import akka.pattern.pipe
import scalax.io._
import java.io.File
import akka.event.Logging
import akka.event.LoggingBus
import akka.event.LoggingAdapter
import akka.actor.ActorRef

case class NewlineFound(position: Int)

class Reader(fileName: String, log: LoggingAdapter, fileReader: ActorRef) {

  def readFile {
    val file: Seekable = Resource.fromFile(new File(fileName))
    val chars = file.chars()
    var position = 0
    chars.foreach({ char =>
      char match {
        case '\n' => {
          fileReader ! NewlineFound(position)
        }
        case _ => // noop
      }
      position = position + 1
    })
  }
}

class FileReader(fileName: String) extends Actor {
  import context.dispatcher

  val log = Logging(context.system, this)

  def receive = {
    case StartReading => {
      log.info("ReadFile message received")
      val future = Future {
        val reader = new Reader(fileName, log, self)
        reader.readFile
      }
      future onSuccess {
        case _ => log.info("success")
      }
    }
    case NewlineFound(position: Int) => {
      log.info("Found newline in position " + position.toString())
    }
  }
}