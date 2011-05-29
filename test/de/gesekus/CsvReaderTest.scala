package de.gesekus
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class CsvReaderSpec extends Spec with ShouldMatchers {

  describe("A CsvReader") {

    describe("(when parsing an empty file)") {

      val csvReader = new CsvReader

      it("should yield an empty list") {
        val s = Source.fromChars("".toCharArray)
        val list = csvReader.parse(s)
        assert(list.isEmpty)
      }
    }

    describe("when parsing three lines") {

      val csvReader = new CsvReader
      val file = "Test11;Test12\nTest21;Test22\nTest31;Test32\n"
      val s = Source.fromChars(file.toCharArray)
      val list = csvReader.parse(s)

      it("should yield a list with 3 elements") {

        list.size should be(3)
      }

      it("should have the right contents in the first element") {
        list(0) should be(Array("Test11", "Test12"))
        list(1) should be(Array("Test21", "Test22"))
        list(2) should be(Array("Test31", "Test32"))
      }
    }
  }
}
