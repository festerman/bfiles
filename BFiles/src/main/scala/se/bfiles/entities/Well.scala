package se.bfiles.entities

class Well (val plate: Plate, val row: Integer, val col: Integer) extends Entity {
	private var _sample: Sample = null
	
	
}