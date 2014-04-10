package se.bfiles.entities

class Plate extends GridLocation with Barcodable with Storable {
	val rows: Integer = 8
	val cols: Integer = 12
}