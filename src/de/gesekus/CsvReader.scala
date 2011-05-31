package de.gesekus
import scala.io.Source

class CsvReader {
	def parse(source: Source) : Array[List[String]] = {
	  val splittedLines = for (line <- source.getLines()) 
	   yield line.split(";").toList
	  splittedLines.toArray
	}
}