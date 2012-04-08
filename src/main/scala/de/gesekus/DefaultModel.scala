package de.gesekus
import scala.collection.mutable.ArrayBuffer

class DefaultModel() extends Model {
  var theHeader: Line = null
  var theBody = new ArrayBuffer[Line]()
  var theColumnWidths: Array[Int] = null

  /**
   * returns the size of the model
   * @return the size of the model
   */
  def size = {
    ModelSize(theBody.size,false)
  }

  def body(index: Int): Line = {
    require(index < theBody.size)
    theBody(index)
  }

  def addLine(line: Line) {
    require(theHeader != null)
    require(line.size <= header.size)
    theBody += line
    adjustColumnWidths(line)
  }

  def header = {
    require(theHeader != null)
    theHeader
  }

  def header(header: Line) {
    require(theHeader == null)
    theHeader = header
    theColumnWidths = new Array[Int](theHeader.size)
    adjustColumnWidths(header)
  }

  def numberOfColumns = {
    require(header != null)
    header.size
  }

  def isEmpty = {
    if (theHeader == null) {
      true
    } else {
      false
    }
  }

  def adjustColumnWidths(line: Line) {
    var i = 0
    for (column <- line) ({
      theColumnWidths(i) = theColumnWidths(i).max(column.size)
      i += 1
    })
  }
  /**
   * @return the widths of the columns
   */
  def columnWidths(): Array[Int] = theColumnWidths

}

object DefaultModel {
  def apply() = {
    new DefaultModel()
  }
}