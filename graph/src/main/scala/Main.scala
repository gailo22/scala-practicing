object Main extends App {

  val devide = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = x != 0

    override def apply(v1: Int): Int = 42 / v1
  }

  private val i = devide.apply(0)
  println(i)

}
