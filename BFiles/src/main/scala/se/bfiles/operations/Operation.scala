package se.bfiles.operations

import se.bfiles.entities.Entity
import se.bfiles.operators.Operator

trait Operation extends Entity {
    var _operator:Operator = null
  
  def operator(op: Operator) = {this._operator = op}
  def operator: Operator = this._operator
  	def wasDoneBy (operator: Operator): Boolean = {this._operator == operator}


}