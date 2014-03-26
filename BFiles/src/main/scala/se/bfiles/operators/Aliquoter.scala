package se.bfiles.operators

import se.bfiles.entities.Sample
import se.bfiles.operations.Aliquoting
import se.bfiles.entities.Aliquot

trait Aliquoter extends Operator {
 def aliquot (sample: Sample, number: Integer): Aliquoting = {
    var aliquots = List[Aliquot]()
    	
    var i = 0
    for ( i <- 1 to number) { 
      aliquots ::= Aliquot(sample)
    }
    
    Aliquoting(this, sample, aliquots)
  }
 }