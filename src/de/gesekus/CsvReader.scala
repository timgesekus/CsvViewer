package de.gesekus
import scala.io.Source

class CsvReader {
	def parse(source: Source) : List[Array[String]] = {
	  val splittedLines = for (line <- source.getLines()) 
	   yield line.split(";")
	  splittedLines.toList
	}
}