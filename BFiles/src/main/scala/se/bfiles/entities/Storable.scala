package se.bfiles.entities

trait Storable {

  	var _location: Storage = null;

  	def store(location: Storage) = {_location = location}
	def unStore(): Storage = {val l = _location; _location = null; l}
	def storedIn(location: Storage): Boolean = {location == _location}
  	def location(): Storage = _location
}