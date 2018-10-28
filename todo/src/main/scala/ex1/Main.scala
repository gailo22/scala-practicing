package ex1


object Main extends App {

  trait Printable[T] {
    def format(value: T): String
  }

  object PrintableInstances {
    implicit val intInstance = new Printable[Int] {
      override def format(value: Int): String = value.toString
    }

    implicit val stringInstance = new Printable[String] {
      override def format(value: String): String = s"-- $value --"
    }
  }

  object Printableface {
    def print[T](value: T)(implicit printable: Printable[T]) =
      println(printable.format(value))
  }

  import PrintableInstances._

  Printableface.print(24)
  Printableface.print("Hello")

  implicit class PrintableSyntax[T](value: T) {
    def print(implicit printable: Printable[T]) =
      println(printable.format(value))
  }

  "world".print

}

