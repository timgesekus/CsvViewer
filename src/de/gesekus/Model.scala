package de.gesekus

abstract class Model {
  type Line = List[String]

  def size: Int
  def body(index: Int): Line
  def body() : Array[Line]
  def addLine(line: Line)
  def header: Line
  def numberOfColumns: Int
  def isEmpty: Boolean
  def columnWidths: Array[Int]
}