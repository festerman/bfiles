package se.bfiles.entities

trait Storable {

  	var _location: Location = null;

  	def store(location: Location) = {_location = location}
	def unStore(): Location = {val l = _location; _location = null; l}
	def storedIn(location: Location): Boolean = {location == _location}
  	def location(): Location = _location
}