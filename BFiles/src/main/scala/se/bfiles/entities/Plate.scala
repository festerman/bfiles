package se.bfiles.entities

class Plate extends GridLocation with Barcodable {
	val rows: Integer = 8
	val cols: Integer = 12
}