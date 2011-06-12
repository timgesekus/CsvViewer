package de.gesekus

/**
 * Views pages of a given model
 * @param model the model to view
 * @author Tim
 *
 */
class PageRenderer(model: DefaultModel, pageSize : Int) {
  require(model != null)
  require(!model.isEmpty)

  /**
   * Pads the given line to the correct width
   * @param line the line to pad
   * @return the paded line
   */
  def padLine(line: List[String]) = {
    for (i <- 0 to numColumns - 1)
      yield ({
      val collumWidth = model.columnWidths()(i)
      line(i).padTo(collumWidth, " ").mkString
    })
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
    model.numberOfColumns
  }

  /**
   * @return the header of the page
   */
  def header = {
    padAndSeperateLine(model.header) + "\n"
  }

  /**
   * @return the separator of the page
   */
  def seperator = {
    val fields = for (i <- 0 to numColumns - 1)
      yield ("-".padTo(model.columnWidths()(i), "-").mkString)
    fields.mkString("+") + "+\n"
  }

  /**
   * Returns the body of a page
   * @param fromLineNumber the line number to begin with
   * @param toLineNumber the line number to end
   * @return the body of a page
   */
  def body(pageNumber : Int): String = {
    val fromLineNumber = (pageNumber  - 1) * pageSize 
    val toLineNumber = fromLineNumber + pageSize -1 
    val lines = for (i <- fromLineNumber to toLineNumber)
      yield (padAndSeperateLine(model.body(i)))
    lines.mkString("\n")
  }

  /**
   * @return the footer
   */
  def footer = {
    "N(ext page, P(revious page, F(irst page, L(ast page, J(ump to page eX(it\n"
  }

  def pageCounter(pageNumber: Int) : String = {
    "Page " + pageNumber + " of " + numberOfPages + "\n"
  }
  /**
   * Returns a page identified by it's number
   * @param pageNumber the page to render
   * @return a page
   */
  def page(pageNumber: Int): String = {
    require(pageNumber > 0)
    require(pageNumber <= numberOfPages)

    header + seperator + body(pageNumber) + "\n" + pageCounter(pageNumber) + "\n" +  footer
  }
  
  def numberOfPages = {
    model.size / pageSize
  }
}