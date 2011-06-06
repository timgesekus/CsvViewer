package de.gesekus
import scala.io.Source

class CsvViewerApp(fileName: String, pageSize: Int) {
  private val fileSource = Source.fromFile(fileName)
  private val csvReader = new CsvReader
  private val model = csvReader.parse(fileSource)
  private val csvViewer = new CsvPageViewer(model)
  private var theCursorPosition = 1

  def cursorPosition_=(newPosition: Int) = {
    println ("newpos:" + newPosition)
    println ("Curpos:" + theCursorPosition)
    theCursorPosition = newPosition.min(lastPageStartPosition)
    println ("Curpos:" + theCursorPosition)
    theCursorPosition = 1.max(cursorPosition)
    println ("Curpos:" + theCursorPosition)
  }
  def cursorPosition = theCursorPosition

  def maximumCursorPosition = {
    (model.size - 1)
  }
  
  def minimumCursorPosition = {
    1
  }
  
  def endPagePosition = {
    maximumCursorPosition.min(cursorPosition + pageSize + 1)
  }
  
  def printPage {
    println(csvViewer.page(cursorPosition, endPagePosition))
  }
  
  def lastPageStartPosition = {
    maximumCursorPosition - pageSize - 1 
  }
  def run {
    var doExit = false
    while (!doExit) {
      printPage
      readChar match {
        case 'n' => cursorPosition += pageSize
        case 'p' => cursorPosition -= pageSize
        case 'f' => cursorPosition = minimumCursorPosition
        case 'l' => cursorPosition = lastPageStartPosition
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
