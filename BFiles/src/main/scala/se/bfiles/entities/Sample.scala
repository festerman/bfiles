package se.bfiles.entities

class Sample extends Entity with Storable

class PrimarySample (val donor: Donor) extends Sample

class Aliquot extends Sample {
  var _parent:Sample = null
  
  def parent(parent:Sample) = {_parent = parent }
  def parent(): Sample = _parent
}

object Aliquot {
  def apply (parent: Sample): Aliquot = {
    val a = new Aliquot
    a.parent(parent)
    a
  }
}

object PrimarySample {
	def apply (donor: Donor): PrimarySample = {
      new PrimarySample(donor)
    }
  
}