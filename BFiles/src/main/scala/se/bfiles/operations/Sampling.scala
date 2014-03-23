package se.bfiles.operations

import se.bfiles.entities.{Donor,PrimarySample}
class Sampling (val donor: Donor, val samples: Set[PrimarySample]) extends Operation {

}

object Sampling {
  def apply (donor: Donor, samples: Set[PrimarySample]): Sampling = {
    new Sampling(donor, samples)
  }
  
  def sampling (donor: Donor, samples: Set[PrimarySample]): Sampling = Sampling(donor, samples)
}