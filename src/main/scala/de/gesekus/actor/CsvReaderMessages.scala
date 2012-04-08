package de.gesekus.actor

sealed trait CsvReaderMessages
case object Lala extends CsvReaderMessages
case class LineRead(lineNumber: Int, isLastLine: Boolean)
object StartReading
case class ViewFile(fileName: String)
