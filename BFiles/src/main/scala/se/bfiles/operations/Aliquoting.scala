package se.bfiles.operations

import se.bfiles.entities.{Sample,Aliquot}
import se.bfiles.operators.Aliquoter

class Aliquoting (val input: Sample, val aliquots: List[Aliquot]) extends Operation {
	var _aliquoter: Aliquoter = null
	def aliquoterWas(a: Aliquoter) = {this._aliquoter = a}
	def wasDoneBy (aliquoter: Aliquoter): Boolean = {this._aliquoter == aliquoter }

}

object Aliquoting {

	def apply (aliquoter:Aliquoter, sample: Sample, aliquots: List[Aliquot]): Aliquoting = {
		val a = Aliquoting(sample, aliquots)
		a aliquoterWas aliquoter
		a
	}

	def apply (sample: Sample, aliquots: List[Aliquot]): Aliquoting = {
		val a = new Aliquoting (sample, aliquots)
		a
	}


}