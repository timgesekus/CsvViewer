package de.gesekus
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class DefaultModelReaderSpec extends Spec with ShouldMatchers {

  describe("A CsvReader") {

    describe("(when parsing an empty file)") {

      val model = new DefaultModel
      val csvReader = new DefaultModelReader(model)

      it("should yield an empty list") {
        val s = Source.fromChars("".toCharArray)
        csvReader.parse(s)
        assert(model.isEmpty)
      }
    }

    describe("when parsing three lines") {

      val model = new DefaultModel
      val csvReader = new DefaultModelReader(model)
     val file = "Header11;Header12\nTest21;Test22\nTest31;Test32\n"
      val s = Source.fromChars(file.toCharArray)
      csvReader.parse(s)

      it("should yield a list with 3 elements") {

        model.size should be(2)
      }

      it("should have the right contents in the body") {
        model.body(0) should be(List("Test21", "Test22"))
        model.body(1) should be(List("Test31", "Test32"))
      }
      
      it("should have the right header") {
        model.header should be(List("Header11","Header12"))
      }
    }
  }
}
