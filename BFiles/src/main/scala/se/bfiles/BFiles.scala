package se.bfiles

import se.bfiles.entities._
import se.bfiles.operations.Aliquoting.{aliquot => aliquot}
import se.bfiles.operations.Sampling.{sampling => sample}

import se.bfiles.operations._
import scala.collection.immutable.HashSet

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
  
  val ali = aliquot(sample1, 6)
  val aliquots = ali.aliquots
  
  assert(aliquots.head.parent.location == tube1)
  
}