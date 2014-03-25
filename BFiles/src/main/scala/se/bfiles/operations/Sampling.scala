package se.bfiles.operations

import se.bfiles.entities.{Donor,PrimarySample}
class Sampling (val donor: Donor, val samples: Set[PrimarySample]) extends Operation {

}

object Sampling {
  def apply (donor: Donor, samples: Set[PrimarySample]): Sampling = {
    val s = new Sampling(donor, samples)
    donor.sample(s)
    s
  }
  
  def sample (donor: Donor, samples: Set[PrimarySample]): Sampling = {
    val sampling = Sampling(donor, samples)
    donor.sample(sampling)
    sampling
  }
}