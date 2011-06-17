package de.gesekus

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class DefaultModelSpec extends Spec with ShouldMatchers {
  describe("A model")({
    describe("when created") {
      val model = DefaultModel()
      it("should be empty") {
        model.isEmpty should be(true)
      }
      it("body size should be 0") {
        model.size should be(0)
      }
      it("should throw an exception when getting a line") {
        evaluating { model.body(0) } should produce[IllegalArgumentException]
      }

      it("should throw an exception when getting the number columns") {
        evaluating { model.numberOfColumns } should produce[IllegalArgumentException]
      }
    }

    describe("when filled with a header") {
      val model = DefaultModel()
      val header = List("A", "B")
      model.header(header)
      it("should have a size of 0") {
        model.size should be(0)
      }
      it("should return a coresponding header") {
        model.header should be(header)
      }

      it("should have 2 columns") {
        model.numberOfColumns should be(2)
      }
    }

    describe("when filled with a header and body") {
      val model = DefaultModel()
      val header = List("A", "B", "C")
      val bodyLine1 = List("Body11", "Body12")
      val bodyLine2 = List("Body21", "Body22", "Body23dummy")
      model.header(header)
      model.addLine(bodyLine1)
      model.addLine(bodyLine2)

      it("should have a size of 2") {
        model.size should be(2)
      }
      it("should return a coresponding header") {
        model.header should be(header)
      }

      it("should return a coresponding body lines") {
        model.body(0) should be(bodyLine1)
        model.body(1) should be(bodyLine2)
      }
      it("should have 2 columns") {
        model.numberOfColumns should be(3)
      }

      it("should have correct columns widths") {
        model.columnWidths should be(Array(6, 6, 11))
      }
    }
  })

}