package se.bfiles

import org.specs2.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import se.bfiles.entities._
import se.bfiles.operations._
import scala.collection.immutable.HashSet

@RunWith(classOf[JUnitRunner])
class BFilesSpec extends Specification {
	isolated


	def is = s2"""
BFiles is a Biobanking system. Entities:
	
	Samples have id                                         	 $e1
	... and are storable                                         $e2
	Aliquots have parent										 $e3
	PrimarySamples have a Donor									 $e4
	Donor have id												 $e5
	... know if (s)he was sampled  								 $e6
	... know if (s)he was not sampled							 $e7
	... recognize if (s)he was part of a specific sampling		 $e8
	"""

	def e1 = {
			val idstring = "id1"
					val s = new Sample()
			s.id(idstring)
			s.id must_== idstring
	}
	
	def e2 = {
			val s = new Sample()
			val t = new Tube()
			t + s
			s.location must_== t
	}
	
	def e3 = {
			val s = new Sample()
			val a = Aliquot(s)
			a.parent must_== s
	}
	
	def e4 = { 
			val d = new Donor()
			val p = PrimarySample(d)
			p.donor must_== d 
	}

	def e5 = {
			val d = new Donor ()
			val idstring = "id"
			var samples1 = new HashSet[PrimarySample]()
			samples1 += new PrimarySample(d)
			d.id(idstring)
			d.id() must_== idstring
	}

	def e6 = {
			val d = new Donor ()
			var samples1 = new HashSet[PrimarySample]()
			samples1 += new PrimarySample(d)
			val s = Sampling(d,samples1)
			d.wasSampled must beTrue
	}

	def e7 = {
			val d = new Donor ()
			d.wasSampled must beFalse
	}

	def e8 = {
			val d = new Donor ()
			var samples1 = new HashSet[PrimarySample]()
			samples1 += new PrimarySample(d)
			val s = Sampling(d,samples1)
			d.wasSampled(s) must beTrue
	}
}