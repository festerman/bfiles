package se.bfiles.entities

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import scala.collection.immutable.HashSet
import se.bfiles.operations.Sampling

@RunWith(classOf[JUnitRunner])
class DonorSpec extends Specification {
	isolated
	
	val d = new Donor ()
	val idstring = "id"
	var samples1 = new HashSet[PrimarySample]()
	samples1 += new PrimarySample(d)

  "A Donor " should {
    "have id" in {
      d.id(idstring)
      d.id() must_== idstring
    }
    
   "know if (s)he was sampled" in {
	 val s = Sampling(d,samples1)
	 d.wasSampled must beTrue
   }

   "know if (s)he was not sampled" in {
	 d.wasSampled must beFalse
   }

   "recognize if (s)he was part of a specific sampling" in {
     val s = Sampling(d,samples1)
     d.wasSampled(s) must beTrue
   }
  }

}