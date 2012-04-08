package de.gesekus
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scalax.io._
import java.io.File

@RunWith(classOf[JUnitRunner])
class IOTest extends Spec with ShouldMatchers {
  describe("A Seekable") {
    describe("when initialzed with csv.dat") {
      val input: Input = Resource.fromFile(new File("data/csv.dat"))
      it("should  return 30 lines") {
        var counter = 0
        val lines = input.lines()
        lines.foreach(line =>
          {
            counter += 1
          })
        counter should be(30)
      }
      
    }
  }
}
