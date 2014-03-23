package se.bfiles.entities

trait Barcodable {
	var _barcode: String = null;
	
	def barcode(barcode: String) = {_barcode = barcode }
	def barcode(): String = _barcode

}