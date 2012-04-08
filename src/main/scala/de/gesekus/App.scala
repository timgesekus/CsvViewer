package de.gesekus
import scala.io.Source


object App {
  def main(args: Array[String]): Unit = {
    require(args.size > 0 && args.size < 3)
    val fileSource = Source.fromFile(args(0))
    val pageSize = if (args.size == 2) {
      args(1).toInt
    } else {
      20
    }
    var csvViewerApp = new CsvViewer(args(0), pageSize)
    csvViewerApp.run
  }

}