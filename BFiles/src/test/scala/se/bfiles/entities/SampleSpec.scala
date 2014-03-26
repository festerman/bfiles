package se.bfiles.entities

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SampleSpec extends Specification {

	"A Sample " should {
		"have id" in {
			val idstring = "id1"
					val s = new Sample()
			s.id(idstring)
			s.id must_== idstring
		}
		"be storable" in {
			val s = new Sample()
			val t = new Tube()
			t + s
			s.location must_== t
		}
	}

	"An Aliquot " should {
		"have a parent" in {
			val s = new Sample()
			val a = Aliquot(s)
			a.parent must_== s
		}
	}

	"A PrimarySample " should {
		"have a donor" in {
			val d = new Donor()
			val p = PrimarySample(d)
			p.donor must_== d
		}
	}
}