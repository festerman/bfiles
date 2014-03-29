package se.bfiles.operations

import se.bfiles.entities.{Donor,PrimarySample}

class Sampling extends Operation {
  private var _donor: Donor = null
  private var _samples: Set[PrimarySample] = Set.empty[PrimarySample]
  
  def donor(d: Donor): Unit = _donor = d
  def donor = _donor
  
  def samples(s: Set[PrimarySample]) = _samples = s
  def sample(s: PrimarySample) = _samples + s
  def samples = _samples
  
  def isComplete = _samples != Set.empty
  
}

object Sampling {
  def apply (donor: Donor, samples: Set[PrimarySample]): Sampling = {
    val s = new Sampling()
    s.samples(samples)
    s.donor(donor)
    donor.sample(s)
    s
  }
  
  def sample (donor: Donor, samples: Set[PrimarySample]): Sampling = {
    val sampling = Sampling(donor, samples)
    donor.sample(sampling)
    sampling
  }
}