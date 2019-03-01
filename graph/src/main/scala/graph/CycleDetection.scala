package graph

case class DfsCycleResult[V](visited: Set[V], isCycle: Boolean = false)

object CycleDetection {

  def containsCycleDfs[V](vertex: V, graph: Graph[V],
                          visited: Set[V] = Set(),
                          ancestors: Set[V] = Set()): DfsCycleResult[V] = {

    if (ancestors.contains(vertex)) DfsCycleResult(visited, isCycle = true)
    else if (visited.contains(vertex)) DfsCycleResult(visited)
    else {
      graph.neighbours(vertex).foldLeft(DfsCycleResult(visited + vertex)) {
        case (DfsCycleResult(v, true), _) => DfsCycleResult(visited, isCycle = true)
        case (currentResult, n) => containsCycleDfs(n, graph, currentResult.visited, ancestors + vertex)
      }
    }
  }

  def containsCycleDfs[V](graph: Graph[V]): Boolean = {
    val startNodes = graph.vertices.filter(v => graph.edges.forall(e => e._2 != v))
    startNodes.isEmpty || startNodes.exists(n =>
      CycleDetection.containsCycleDfs(n, graph).isCycle)
  }

}

