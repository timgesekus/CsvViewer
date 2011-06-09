package de.gesekus
import scala.io.Source

/**
 * Parses a csv file
 * @author Tim
 *
 */
class Reader {
	/**
	 * Parse the given source
	 * @param source the source to parse
	 * @return the parsed source
	 */
	def parse(source: Source) : Model = {
	  var model = Model()
	  for (line <- source.getLines()) {
	   model.addLine(line.split(";").toList)
	  }
	  model
	}
}