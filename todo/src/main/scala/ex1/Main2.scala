package ex1

object Main2 extends App {

  trait Printable[T] {
    def format(value: T): String
  }

  case class Cat(name: String, age: Int, color: String)

  object PrintableInstances {
    implicit val catInstance = new Printable[Cat] {
      override def format(value: Cat): String =
        s"${value.name} is a ${value.age} year old ${value.color} cat"
    }
  }

  implicit class PrintableSyntax[T](value: T) {
    def print(implicit printable: Printable[T]) =
      println(printable.format(value))
  }

  import PrintableInstances._

  Cat("Tuptup", 1, "gray").print

}
