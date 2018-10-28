package ex2

object Main3 extends App with Data {

  trait Addable[T] {
    def add(a: T, b: T): T
  }

  implicit val addMoneyIn = new Addable[Money] {
    override def add(a: Money, b: Money): Money =
      Money((a.dollar + b.dollar) + (a.cents + b.cents) / 100,
        (a.cents + b.cents) % 100)
  }

  implicit val addIntIn = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  implicit def addMaps[K, V: Addable] = new Addable[Map[K, V]] {
    override def add(a: Map[K, V], b: Map[K, V]): Map[K, V] = {
      a.foldLeft(b) {
        case (acc, (k, v)) =>
          acc + (k -> acc.get(k).fold(v)(implicitly[Addable[V]].add(_, v)))
      }
    }
  }

  def add[A: Addable](a: A, b: A)(implicit addable: Addable[A]): A =
    addable.add(a, b)

  println(s"Credit salary into your account: ${add(balances, salaries)}")
  println(s"Marble are credit into your game account: ${add(marbles, won)}")
  println(s"Add only Money: ${add(Money(76, 22), Money(54, 21))}")

}
