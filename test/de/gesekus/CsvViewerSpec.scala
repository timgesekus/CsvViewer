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
        evaluating { new CsvViewer(null) } should produce[IllegalArgumentException]
      }
    }
    describe("when created with empty model") {
      val model = Nil
      it("should throw an exception") {
        evaluating { new CsvViewer(model) } should produce[IllegalArgumentException]
      }
    }
    describe("when created with only a header line") {
      val model = Array("Test1", "Test2") :: Nil
      val csvView = new CsvViewer(model)

      it("should return a page with only a header and menubar") {
        csvView.screen should be(
          "Test1|Test2|\n" +
            "-----+-----+\n" +
            "\n" +
            "N(ext page, P(revious page, F(irst page, L(ast page, eX(it")
      }
    }
  }
}