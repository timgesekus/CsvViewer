package de.gesekus
import scala.io.Source

class CsvViewer(fileName: String, pageSize: Int) {
  private val fileSource = Source.fromFile(fileName)
  private val csvReader = new Reader
  private val model = csvReader.parse(fileSource)
  private val csvViewer = new PageRenderer(model)
  private var theCursorPosition = 0

  def cursorPosition_=(newPosition: Int) = {
    println ("newpos:" + newPosition)
    println ("Curpos:" + theCursorPosition)
    theCursorPosition = newPosition.min(lastPageStartPosition)
    println ("Curpos:" + theCursorPosition)
    theCursorPosition = minimumCursorPosition.max(cursorPosition)
    println ("Curpos:" + theCursorPosition)
  }
  def cursorPosition = theCursorPosition

  def maximumCursorPosition = {
    (model.size)
  }
  
  def minimumCursorPosition = {
    0
  }
  
  def endPagePosition = {
    maximumCursorPosition.min(cursorPosition + pageSize - 1)
  }
  
  def printPage {
    println(csvViewer.page(cursorPosition, endPagePosition))
  }
  
  def lastPageStartPosition = {
    maximumCursorPosition - pageSize  
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


