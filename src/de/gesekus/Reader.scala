package de.gesekus
import scala.io.Source

/**
 * Parses a csv file
 * @author Tim
 *
 */
class Reader (model : Model) {
	/**
	 * Parse the given source
	 * @param source the source to parse
	 * @return the parsed source
	 */
	def parse(source: Source)  {
	  for (line <- source.getLines()) {
	   model.addLine(line.split(";").toList)
	  }
	}
}