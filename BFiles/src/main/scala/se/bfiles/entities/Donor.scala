package se.bfiles.entities

import se.bfiles.operations.Sampling
import scala.collection.immutable.HashSet

class Donor extends Entity {
  var samplings: Set[Sampling] = new HashSet[Sampling]()

  def sample(sampling: Sampling): Unit = samplings += sampling
  def wasSampled(sampling: Sampling): Boolean = samplings.contains(sampling)
  def wasSampled():Boolean = samplings.size > 0
}