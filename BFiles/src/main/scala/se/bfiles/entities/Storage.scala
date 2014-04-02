package se.bfiles.entities

trait Storage extends Location {
  private var _content: Storable = null
  
  def store(storable: Storable): Unit = {
    _content = storable
    storable store this
  }

  def unstore(): Storable = {
    _content unStore
    val s = _content
    _content = null
    s
  }
  
  def has(storable: Storable): Boolean = {
    storable == _content
  }
}