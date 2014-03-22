package se.bfiles

class BFiles {
  val greeting = "Welcome to BFiles!"
  def greet = { println(greeting) }
}

object BFiles extends App {
  val greeter = new BFiles
  greeter.greet
}