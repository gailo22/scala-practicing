import graph.Topological.DfsStep
import graph.{CycleDetection, Graph, Topological, Traversal}

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

// Topological sort
val result = g.vertices
.foldLeft(DfsStep())((step, n) => Topological.topoDfsSort(n, step, g)).sort

val g1 = Graph(Map("A" -> List("B", "C", "D"),
  "B" -> List("D"),
  "C" -> List("D"),
  "D" -> List("C"),
  "E" -> List("C"),
))

// Cycle detection
val startNodes = g1.vertices.filter(v => g1.edges.forall(e => e._2 != v))
startNodes.isEmpty || startNodes.exists(n =>
  CycleDetection.containsCycleDfs(n, g1).isCycle)

g1.vertices.exists(v => CycleDetection.containsCycleDfs(v, g1).isCycle)