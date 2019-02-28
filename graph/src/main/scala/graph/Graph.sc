import graph.Topological.DfsStep
import graph.{Graph, Topological, Traversal}

val g = Graph[String]()
  .addEdge("London", "Lisbon")
  .addEdge("Lisbon", "Madrid")
  .addEdge("Madrid", "London")
  .addEdge("Madrid", "Rome")
  .addEdge("Rome", "London")
  .addEdge("Paris", "Rome")

g.vertices
g.neighbours("London")

Traversal.traversalDFS("London", g, println)
Traversal.traversalDFSIter("London", g, println)
Traversal.traversalBFS("London", g, println)

val result = g.vertices
.foldLeft(DfsStep())((step, n) => Topological.topoDfsSort(n, step, g)).sort