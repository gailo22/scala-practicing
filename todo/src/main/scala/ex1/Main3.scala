package ex1

import cats.Show
import cats.instances.int._
import cats.instances.string._

object Main3 extends App {

  val showInt: Show[Int] = Show[Int]
  val showString: Show[String] = Show[String]

  println(showInt.show(123))
  println(showString.show("Hello"))

  import cats.syntax.all._

  println("world".show)

}
