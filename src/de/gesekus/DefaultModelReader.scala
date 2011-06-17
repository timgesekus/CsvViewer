package de.gesekus
import scala.io.Source

/**
 * Parses a csv file
 * @author Tim
 *
 */
class DefaultModelReader(model: DefaultModel) {
  /**
   * Parse the given source
   * @param source the source to parse
   * @return the parsed source
   */
  def parse(source: Source) {
    var firstLine = true
    for (line <- source.getLines()) {
      val splittetLine = line.split(";").toList
      if (firstLine) {
        model.header(splittetLine)
        firstLine = false
      } else {
        model.addLine(splittetLine)
      }
    }
  }
}