class BFiles {
  val greeting:String = "Welcome to BFiles!"
    def greet() {
    println(greeting)
  }
}
object BFiles extends App {
  val greeter = new BFiles ()
  greeter.greet
}