package se.bfiles.operators

import org.specs2.mutable.Specification
import se.bfiles.entities.Sample
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AliquoterSpec extends Specification {

  class Tester extends Aliquoter {}
  
	"An Aliquoter " should {
	  "be able to aliquot a sample" in {
	    val a = new Tester()
	    val s = new Sample()
	    val ali = a.aliquot(s, 2)
	    ali.aliquots.size must_== 2
	    ali.input must_== s
	   }
	}
}