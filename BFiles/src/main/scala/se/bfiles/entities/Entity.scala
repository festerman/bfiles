package se.bfiles.entities

trait Entity {
	var _id: String = null;
	
	def id (id: String): Unit = { _id = id }
	def id (): String = _id
	
}