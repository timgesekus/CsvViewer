package de.gesekus
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class TableSpec extends Spec with ShouldMatchers {
  describe("A CsvReader") {
    describe("when created") {

      val table = new Table
      it("should be empty") {
        assert(table.isEmpty)
      }
    }
  }

}