package se.bfiles

import se.bfiles.entities._
import se.bfiles.operations.Sampling.{sample => sample}
import se.bfiles.operations._
import scala.collection.immutable.HashSet
import se.bfiles.operators.Aliquoter

class BFiles {
  val greeting = "Welcome to BFiles!"
  def greet = { println(greeting) }
}

object BFiles extends App {
  val greeter = new BFiles
  greeter.greet
  
  val donor = new Donor
  val sample1 = PrimarySample(donor)
  val sample2 = PrimarySample(donor)
  var samples = new HashSet[PrimarySample]()
  samples += sample1
  samples += sample2
  
  val tube1 = new Tube
  tube1 + sample1
  val tube2 = new Tube
  tube2 + sample2
  
  tube1.barcode("barcode1")
  tube2.barcode("barcode2")
  
  assert(tube1.barcode == "barcode1")
  assert(tube1 has sample1)
  assert(tube2 has sample2)
  
  val sampling = sample(donor, samples)
  sampling.id("sampling1")
  
  val golem = new Robot with Aliquoter
  
  
  val ali = golem.aliquot(sample1, 6)
  ali.id("ali1")
  val aliquots = ali.aliquots
  
  assert(aliquots.head.parent.location == tube1)
  
  assert(donor wasSampled sampling)
  assert(donor wasSampled)
  
  assert(ali wasDoneBy golem)
}