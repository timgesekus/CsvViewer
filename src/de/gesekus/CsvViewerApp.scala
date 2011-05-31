package de.gesekus
import scala.io.Source
class CsvViewerApp(fileName: String, pageSize: Int) {
  private val fileSource = Source.fromFile(fileName)
  private val csvReader = new CsvReader
  private val model = csvReader.parse(fileSource)
  private val csvViewer = new CsvViewer(model)
  private var theCursorPosition = 1

  def cursorPosition_=(newPosition: Int) = {
    println ("newpos:" + newPosition)
    println ("Curpos:" + theCursorPosition)
    theCursorPosition = newPosition.min(model.size-2-pageSize)
    println ("Curpos:" + theCursorPosition)
    theCursorPosition = 1.max(cursorPosition)
    println ("Curpos:" + theCursorPosition)
  }
  def cursorPosition = theCursorPosition

  def endPagePosition() = {
    (model.size - 2).min(cursorPosition + pageSize)
  }
  
  def printPage {
    println(csvViewer.screen(cursorPosition, endPagePosition))
  }
  
  def endPosition = {
    model.size-2
  }
  def run {
    var doExit = false
    while (!doExit) {
      printPage
      readChar match {
        case 'n' => cursorPosition += pageSize
        case 'p' => cursorPosition -= pageSize
        case 'f' => cursorPosition = 1
        case 'l' => cursorPosition = endPosition
        case 'x' => doExit = true
        case _ =>
      }
    }
  }
}

object CsvViewerApp {
  def main(args: Array[String]): Unit = {
    require(args.size > 0 && args.size < 3)
    val fileSource = Source.fromFile(args(0))
    val pageSize = if (args.size == 2) {
      args(1).toInt
    } else {
      20
    }
    var csvViewerApp = new CsvViewerApp(args(0), pageSize)
    csvViewerApp.run
  }

}
