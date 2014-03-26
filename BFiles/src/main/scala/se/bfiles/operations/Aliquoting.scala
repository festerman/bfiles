package se.bfiles.operations

import se.bfiles.entities.{Sample,Aliquot}
import se.bfiles.operators.Aliquoter

class Aliquoting (val input: Sample, val aliquots: List[Aliquot]) extends Operation {
	def aliquoter (ali: Aliquoter) = {this.operator(ali)}
}

object Aliquoting {

	def apply (aliquoter:Aliquoter, sample: Sample, aliquots: List[Aliquot]): Aliquoting = {
		val a = Aliquoting(sample, aliquots)
		a operator aliquoter
		a
	}

	def apply (sample: Sample, aliquots: List[Aliquot]): Aliquoting = {
		val a = new Aliquoting (sample, aliquots)
		a
	}


}