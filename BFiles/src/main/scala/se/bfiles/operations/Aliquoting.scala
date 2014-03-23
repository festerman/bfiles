package se.bfiles.operations

import se.bfiles.entities.{Sample,Aliquot}

class Aliquoting (val input: Sample, val aliquots: List[Aliquot]) extends Operation {
}

object Aliquoting {
  
  def apply (sample: Sample, aliquots: List[Aliquot]): Aliquoting = {
    val a = new Aliquoting (sample, aliquots)
    a
  }
  
  def aliquot (sample: Sample, number: Integer): Aliquoting = {
    var aliquots = List[Aliquot]()
    	
    var i = 0
    for ( i <- 1 to number) { 
      aliquots ::= Aliquot(sample)
    }
    
    Aliquoting(sample, aliquots)
  }
}