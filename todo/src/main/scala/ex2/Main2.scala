package ex2

object Main2 extends App with Data {

  def addMoney(a: Money, b: Money): Money = {
    Money((a.dollar + b.dollar) + (a.cents + b.cents) / 100,
      (a.cents + b.cents) % 100)
  }

  trait Addable[T] {
    def add(a: T, b: T): T
  }

  def addMaps[K, V](balances: Map[K, V], newMap: Map[K, V])(implicit addable: Addable[V]): Map[K, V] = {
    balances.foldLeft(newMap) {
      case (acc, (k, v)) =>
        acc + (k -> acc.get(k).fold(v)(addable.add(_, v)))
    }
  }

  implicit val addMoneyIn = new Addable[Money] {
    override def add(a: Money, b: Money): Money = addMoney(a, b)
  }

  implicit val addIntIn = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  println(s"Credit salary into your account: ${addMaps(balances, salaries)}")
  println(s"Marble are credit into your game account: ${addMaps(marbles, won)}")

}
