package de.gesekus.actor
import akka.actor.Actor
import akka.actor.Props
import java.io.FileNotFoundException

class Supervisor extends Actor {
  import akka.actor.OneForOneStrategy
  import akka.actor.SupervisorStrategy._
  import akka.util.duration._

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
    case _: ArithmeticException => Resume
    case _: NullPointerException => Restart
    case _: IllegalArgumentException => Stop
    case _: FileNotFoundException => Restart
    case _: Exception => Escalate
  }

  def receive = {
    case ViewFile(fileName: String) => {
      val fileReader = context.actorOf(Props(new FileReader(fileName)), name = "fileReader")
      fileReader ! StartReading
    }
  }
}