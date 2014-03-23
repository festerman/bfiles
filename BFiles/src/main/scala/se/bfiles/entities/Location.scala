package se.bfiles.entities

import scala.collection.immutable.HashSet

trait Location extends Entity {
  	var _content:Set[Storable] = HashSet[Storable]()
  	
  	def put (item: Storable): Set[Storable] = this + item
  	def + (item: Storable): Set[Storable] = {
  	  _content = _content + item
  	  item.store(this)
  	  _content
  	  }
  	def take (item: Storable): Storable = this - item
  	def - (item: Storable): Storable = {
  	  _content = _content - item
  	  item.unStore
  	  item
  	}
  	def has(item: Storable): Boolean = _content.contains(item)

}