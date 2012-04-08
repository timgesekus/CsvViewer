package de.gesekus
import scala.collection.mutable.ArrayBuffer

trait LineNumbers extends Model {

  abstract override def body(index: Int): Line = {
      ((index + 1).toString + ".") :: super.body(index)
  }
  abstract override def header: Line = {
    "No." :: super.header
  }
  
  abstract override def columnWidths : Array[Int] = {
    val origColumnWidths = super.columnWidths
    Array(5,origColumnWidths:_*)
  }
  
}