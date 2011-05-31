package de.gesekus

/**
 * @author Tim
 *
 */
class CsvViewer(model: Array[List[String]]) {
  require(model != null)
  require(model.size > 0)

  def padLine(line: List[String]) = {
    for (i <- 0 to numCollums - 1)
      yield line(i).padTo(widths()(i), " ").mkString
  }
  
  def padAndSeperateLine (line: List[String]) = {
    padLine(line).mkString("|") + "|"
  }
  
  def numCollums = {
    model.head.size
  }

  def widths(): Array[Int] = {
    def maximumValue( array: Array[Int]) = {
      array.foldRight(0)((a, b) => a.max(b))
    }
    val test = for (i <- 0 to numCollums - 1)
      yield ({
      val sizeList = model.map(_(i).size)
      maximumValue(sizeList)
    })
    test.toArray
  }

  def header = {
    padAndSeperateLine(model(0)) + "\n"
  }

  def seperator = {
    val fields = for (i <- 0 to numCollums - 1)
      yield ("-".padTo(widths()(i), "-").mkString)
    fields.mkString("+") + "+\n"
  }
  
  def body(fromLineNumber: Int, toLineNumber: Int ):String =  {
    val lines = for (i <- fromLineNumber to toLineNumber)
    	yield(padAndSeperateLine(model(i)))
    lines.mkString("\n")
  }
  
  def footer = {
    "N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n"
  }

  def screen(fromLineNumber: Int, toLineNumber: Int ): String = {
    if (fromLineNumber >= model.size || toLineNumber >= model.size) {
      throw new IllegalArgumentException("Linenumber out of range")
    }

    header + seperator + body(fromLineNumber, toLineNumber) + "\n" + footer
  }
}