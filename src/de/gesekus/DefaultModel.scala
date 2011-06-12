package de.gesekus
import scala.collection.mutable.ArrayBuffer

class DefaultModel extends Model {

  var theHeader: Line = null
  var theBody = new ArrayBuffer[Line]()

  /**
   * returns the size of the model
   * @return the size of the model
   */
  def size = theBody.size

  def body(index: Int): Line = {
    require(index < body.size)
    body()(index)
  }
  
  def body: Array[Line] = {
    theBody.toArray
  }

  def addLine(line: Line) {
    if (theHeader == null) {
      theHeader = line
    } else {
      theBody += line
    }
  }

  def header = {
    require(theHeader != null)
    theHeader
  }

  def numberOfColumns = {
    val numberOfColumnsBody = body.foldLeft(0)((a, b) => a.max(b.size))
    val numberOfColumnsHeader = if (header == null) 0 else header.size
    numberOfColumnsHeader.max(numberOfColumnsBody)
  }

  def isEmpty = {
    if (header == null) {
      true
    } else {
      false
    }
  }

  /**
   * @return the widths of the columns
   */
  def columnWidths(): Array[Int] = {
    def maximumValue(sizeList: List[Int]) = {
      sizeList.foldLeft(0)((a, b) => a.max(b))
    }
    val columnWidths = for (i <- 0 to numberOfColumns - 1)
      yield ({
      val sizeList = (header ::  body.toList).map(_(i).size)
      maximumValue(sizeList)
    })
    columnWidths.toArray
  }

}

object DefaultModel {
  def apply() = {
    new DefaultModel
  }
}