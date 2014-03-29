package se.bfiles.operations


import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import se.bfiles.operators.Operator

@RunWith(classOf[JUnitRunner])
class OperationSpec extends Specification {
	
	class TestOperation extends Operation {
	  val isComplete = false
	}
	val testOp = new TestOperation
	
	"An Operation " should {
	  "have an id" in {
	    testOp.id("id")
	    testOp.id must_== "id"
	  }
	  "be able to save who did it" in {
		  class TestOperator extends Operator {}
		  val oper = new TestOperator
	    testOp.operator(oper)
	    testOp.operator must_== oper 
	  }
	}
	
}