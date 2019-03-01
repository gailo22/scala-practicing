package graph

object FlowNetwork {

  val vertices = List("S", "A", "B", "C", "D", "T")

  val g = Vector(
    /*    S  A  B  C  D  T */
    /*S*/ 0, 4, 8, 0, 0, 0,
    /*A*/ 0, 0, 0, 6, 0, 0,
    /*B*/ 0, 3, 0, 6, 2, 0,
    /*C*/ 0, 0, 0, 0, 3, 11,
    /*D*/ 0, 0, 0, 0, 0, 2,
    /*T*/ 0, 0, 0, 0, 0, 0,
  )

  val n: Int = vertices.size

  def neighbours(graph: Vector[Int], u: Int): Vector[Int] =
    graph.slice(u * n, u * n + n)

  def edgeAt(graph: Vector[Int], u: Int, v: Int): Int = graph(u * n + v)

  def update(graph: Vector[Int], u: Int, v: Int, w: Int): Vector[Int] =
    graph.updated(u * n + v, w)



}
