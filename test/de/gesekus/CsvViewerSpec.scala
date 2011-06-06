package de.gesekus

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class CsvViewerSpec extends Spec with ShouldMatchers {
  describe("A CsvReader") {
    describe("when created with null model") {
      it("should throw an exception") {
        evaluating { new CsvPageViewer(null) } should produce[IllegalArgumentException]
      }
    }
    describe("when created with empty model") {
      val model = Array[List[String]]()
      it("should throw an exception") {
        evaluating { new CsvPageViewer(model) } should produce[IllegalArgumentException]
      }
    }
    describe("when created with only a header line") {
      val model = Array(List("Test1","Test2"))
      val csvView = new CsvPageViewer(model)

      it("should return a page with only a header and menubar") {
        csvView.page(1,20) should be(
          "Test1|Test2|\n" +
            "-----+-----+\n" +
            "\n" +
            "N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n")
      }
    }
    
    describe("when created with header line") {
      val model = Array(List("Head1","Head2"), List("Body11   ","Body12  "))
      val csvView = new CsvPageViewer(model)

      it("should return a page with only a header and menubar") {
        csvView.page(1,1) should be(
"""Head1    |Head2   |
---------+--------+
Body11   |Body12  |

N(ext page, P(revious page, F(irst page, L(ast page, eX(it
""")
      }
    }
  }
}