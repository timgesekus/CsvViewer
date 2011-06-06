package de.gesekus
import scala.io.Source

/**
 * Parses a csv file
 * @author Tim
 *
 */
class CsvReader {
	/**
	 * Parse the given source
	 * @param source the source to parse
	 * @return the parsed source
	 */
	def parse(source: Source) : Array[List[String]] = {
	  val splittedLines = for (line <- source.getLines()) 
	   yield line.split(";").toList
	  splittedLines.toArray
	}
}