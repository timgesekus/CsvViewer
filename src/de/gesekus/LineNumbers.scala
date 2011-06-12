package de.gesekus
import scala.collection.mutable.ArrayBuffer

trait LineNumbers extends Model {

  abstract override def body: Array[Line] = {
    var i = 0
    for (line <- super.body) yield ({
      i += 1
      i.toString + "." :: line
    })
  }
  abstract override def header: Line = {
    "No." :: super.header
  }

}