package se.bfiles.operations

import se.bfiles.entities.{Sample,Aliquot}
import se.bfiles.operators.Aliquoter

class Aliquoting (val input: Sample) extends Operation {
	private var _aliquots: List[Aliquot] = null
	
	def aliquots(aliquots: List[Aliquot]): Unit = _aliquots = aliquots
	def aliquots(): List[Aliquot] = _aliquots
	def aliquoter (ali: Aliquoter) = {this.operator(ali)}
	def isComplete = {_aliquots != null}
}

object Aliquoting {

	def apply (sample: Sample): Aliquoting = {
	  new Aliquoting(sample)
	}
	
	def apply (aliquoter:Aliquoter, sample: Sample, aliquots: List[Aliquot]): Aliquoting = {
		val a = Aliquoting(sample, aliquots)
		a operator aliquoter
		a
	}

	def apply (sample: Sample, aliquots: List[Aliquot]): Aliquoting = {
		val a = Aliquoting (sample)
		a.aliquots(aliquots)
		a
	}


}