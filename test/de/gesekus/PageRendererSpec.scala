package de.gesekus

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class PageRendererSpec extends Spec with ShouldMatchers {
  describe("A PageRenderer") {
    describe("when created with null model") {
      it("should throw an exception") {
        evaluating { new PageRenderer(null, 5) } should produce[IllegalArgumentException]
      }
    }
    describe("when created with empty model") {
      val model = DefaultModel()
      it("should throw an exception") {
        evaluating { new PageRenderer(model, 5) } should produce[IllegalArgumentException]
      }
    }
    describe("when created with header line") {
      val model = DefaultModel()
      model.addLine(List("Head1","Head2"))
      model.addLine(List("Body11   ","Body12  "))
      val csvView = new PageRenderer(model, 5)

      it("should return a page with only a header and menubar") {
        csvView.page(0) should be(
"""Head1    |Head2   |
---------+--------+
Body11   |Body12  |

N(ext page, P(revious page, F(irst page, L(ast page, eX(it
""")
      }
    }
  }
}