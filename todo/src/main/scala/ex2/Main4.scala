package ex2

import cats.kernel.Semigroup

object Main4 extends App with Data {

  implicit val addMoney = new Semigroup[Money] {
    override def combine(a: Money, b: Money): Money =
      Money((a.dollar + b.dollar) + (a.cents + b.cents) / 100,
        (a.cents + b.cents) % 100)
  }

  import cats.instances.int._
  import cats.instances.map._

  def add[A: Semigroup](a: A, b: A)(implicit semigroup: Semigroup[A]): A =
    semigroup.combine(a, b)

  println(s"Credit salary into your account: ${add(balances, salaries)}")
  println(s"Marble are credit into your game account: ${add(marbles, won)}")
  println(s"Add only Money: ${add(Money(76, 22), Money(54, 21))}")

  import cats.syntax.semigroup._

  println(s"Credit salary into your account New Syntax: ${balances |+| salaries}")
  println(s"Marble are credit into your game account New Syntax: ${marbles |+| won}")


}
