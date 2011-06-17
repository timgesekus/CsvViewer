package de.gesekus
import scala.io.Source

class CsvViewer(fileName: String, pageSize: Int) {
  private val fileSource = Source.fromFile(fileName)
  private val model = new DefaultModel() with LineNumbers
  private val csvReader = new DefaultModelReader(model)
  csvReader.parse(fileSource)
  private val pageRenderer = new PageRenderer(model, pageSize)
  private var theCurrentPageNumber = 1

  def highestPageNumber = pageRenderer.numberOfPages

  def lowestPageNumber = 1

  def currentPageNumber_=(pageNumber: Int) {
    theCurrentPageNumber = highestPageNumber.min(pageNumber)
    theCurrentPageNumber = lowestPageNumber.max(theCurrentPageNumber)
  }

  def currentPageNumber = theCurrentPageNumber

  def printPage {
    println(pageRenderer.page(currentPageNumber))
  }

  def jumpToPage {
    println ("Enter page number:")
    val pageNumber = readInt 
    if (isValidPageNumber(pageNumber)) {
      currentPageNumber = pageNumber
    }
  }
  
  def isValidPageNumber(pageNumber : Int) = {
    if (pageNumber >= lowestPageNumber && pageNumber <= highestPageNumber) {
      true
    } else {
      false
    }
  }
  def run {
    var doExit = false
    while (!doExit) {
      printPage
      readChar match {
        case 'n' => currentPageNumber = currentPageNumber + 1
        case 'p' => currentPageNumber = currentPageNumber - 1
        case 'f' => currentPageNumber = lowestPageNumber
        case 'l' => currentPageNumber = highestPageNumber
        case 'j' => jumpToPage
        case 'x' => doExit = true
        case _ =>
      }
    }
  }
}


