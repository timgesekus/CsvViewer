package de.gesekus

abstract class Model {
  type Line = List[String]

  def size: ModelSize
  def body(index: Int): Line
  def header: Line
  def numberOfColumns: Int
  def isEmpty: Boolean
  def columnWidths: Array[Int]
}