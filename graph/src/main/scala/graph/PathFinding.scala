package graph

import scala.collection.immutable.Queue

object PathFinding {

  import FlowNetwork._

  val parentMap = Map(3 -> 2, 2 -> 1)

  def buildPath(child: Int, parents: Map[Int, Int]): List[(Int, Int)] =
    parents.get(child).map(p => (p, child) +: buildPath(p, parents))
      .getOrElse(Nil)

  buildPath(3, parentMap).reverse

  def findPathBFS(s: Int, t: Int, graph: Vector[Int]): List[(Int, Int)] = {
    val sq = Stream.iterate((Queue(s), Set(s), Map[Int, Int]())) {
      case (q, visited, parents) =>
        val (vertex, tail) = q.dequeue
        val nbours = neighbours(graph, vertex).zipWithIndex
          .collect { case (f, id) if f > 0 => id }.toSet -- visited
        val newQueue = tail ++ nbours
        val newVisited = nbours ++ visited
        val newParents = parents ++ nbours.map(_ -> vertex)
        (newQueue, newVisited, newParents)
    }
    val parentsMap = sq.takeWhile(q => q._1.nonEmpty).last._3
    buildPath(t, parentsMap).reverse
  }

  val source = vertices.indexOf("S")
  val sink = vertices.indexOf("T")

  findPathBFS(source, sink, g).map(e => (vertices(e._1), vertices(e._2)))

}
