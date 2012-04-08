package de.gesekus

class ActorModel extends Model {
  def size: ModelSize = ModelSize(0,false)
  def body(index: Int): Line = "" :: Nil
  def header: Line = "" :: Nil
  def numberOfColumns: Int = 0
  def isEmpty: Boolean = false
  def columnWidths: Array[Int] = Array(0)
}