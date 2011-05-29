package de.gesekus

/**
 * @author Tim
 *
 */
class CsvViewer(model: List[Array[String]]) {
  require(model != null)
  require(model.size > 0)

  def footer = {
    "N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n"
  }

  def header = {
    model(0).mkString("|") + "|\n"
  }

  def screen(): String = {
    val page = header + footer
    page
  }
}