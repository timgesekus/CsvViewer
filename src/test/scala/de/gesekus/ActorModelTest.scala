package de.gesekus
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ActorModelTest extends Spec with ShouldMatchers {
  describe("A modelActor") {
    describe("when created") {
      val modelActor = new ActorModel()
      it("should have a size of 0") {
    	  modelActor.size.size should be(0)
      }
    }
    describe("when initialized with number of lines 5") {
      val modelActor = new ActorModel()
      //modelActor.initialize(")
    }
  }
}