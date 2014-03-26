package se.bfiles.operations

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import se.bfiles.entities.Sample
import se.bfiles.entities.Aliquot
import se.bfiles.operators.Aliquoter

@RunWith(classOf[JUnitRunner])
class AliquotingSpec extends Specification {

  val s = new Sample ()
  var l = new Aliquot() :: Nil
  class TestAliquoter extends Aliquoter {}
  
  "An Aliquoting " should {
    "be able to save and tell which aliquoter did it " in {
      val a = Aliquoting(s, l)
      val ali = new TestAliquoter ()
      a aliquoter ali
      a wasDoneBy ali must beTrue
    }
  }
}