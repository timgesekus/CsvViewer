package de.gesekus.actor
import akka.actor.ActorSystem
import akka.actor.Props

object Testme extends App {
  run

  def run = {
    val system = ActorSystem("CsvReaderSystem")
    val superVisor = system.actorOf(Props[Supervisor], name = "superVisor")
    val resource = getClass().getResource("/test.txt")
    val tableSpec = resource.getPath()
    superVisor ! ViewFile (tableSpec)
  }
}	