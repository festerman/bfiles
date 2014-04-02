package se.bfiles.entities

import scala.collection.immutable.HashSet

trait Location extends Entity {
  	private var _locations:Set[Location] = HashSet[Location]()
  	
  	def put (item: Location): Set[Location] = this + item
  	def + (item: Location): Set[Location] = {
  	  _locations = _locations + item
  	  _locations
  	  }
  	def take (item: Location): Location = this - item
  	def - (item: Location): Location = {
  	  _locations = _locations - item
  	  item
  	}
  	def has(item: Location): Boolean = _locations.contains(item)
}