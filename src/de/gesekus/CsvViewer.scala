package de.gesekus

/**
 * Views pages of a given model
 * @param model the model to view
 * @author Tim
 *
 */
class CsvPageViewer(model: Array[List[String]]) {
  require(model != null)
  require(model.size > 0)

  /**
   * Pads the given line to the correct width
   * @param line the line to pad
   * @return the paded line
   */
  def padLine(line: List[String]) = {
    for (i <- 0 to numColumns - 1)
      yield line(i).padTo(columnWidths()(i), " ").mkString
  }

  /**
   * pads a given line and adds separators
   * @param line the line to modify
   * @return the line
   */
  def padAndSeperateLine(line: List[String]) = {
    padLine(line).mkString("|") + "|"
  }

  /**
   * @return the number of columns
   */
  def numColumns = {
    model.head.size
  }

  /**
   * @return the widths of the columns
   */
  def columnWidths(): Array[Int] = {
    def maximumValue(array: Array[Int]) = {
      array.foldRight(0)((a, b) => a.max(b))
    }
    val columnWidths = for (i <- 0 to numColumns - 1)
      yield ({
      val sizeList = model.map(_(i).size)
      maximumValue(sizeList)
    })
    columnWidths.toArray
  }

  /**
   * @return the header of the page
   */
  def header = {
    padAndSeperateLine(model(0)) + "\n"
  }

  /**
   * @return the separator of the page
   */
  def seperator = {
    val fields = for (i <- 0 to numColumns - 1)
      yield ("-".padTo(columnWidths()(i), "-").mkString)
    fields.mkString("+") + "+\n"
  }

  /**
   * Returns the body of a page
   * @param fromLineNumber the line number to begin with
   * @param toLineNumber the line number to end
   * @return the body of a page
   */
  def body(fromLineNumber: Int, toLineNumber: Int): String = {
    val lines = for (i <- fromLineNumber to toLineNumber)
      yield (padAndSeperateLine(model(i)))
    lines.mkString("\n")
  }

  /**
   * @return the footer
   */
  def footer = {
    "N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n"
  }

  /**
   * Returns a page
   * @param fromLineNumber the line number to begin with
   * @param toLineNumber the line number to end
   * @return a page
   */
  def page(fromLineNumber: Int, toLineNumber: Int): String = {
    if (fromLineNumber >= model.size || toLineNumber >= model.size) {
      throw new IllegalArgumentException("Linenumber out of range")
    }

    header + seperator + body(fromLineNumber, toLineNumber) + "\n" + footer
  }
}