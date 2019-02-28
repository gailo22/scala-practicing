package graph

object Topological {

  case class DfsStep(visited: Set[String] = Set(), sort: List[String] = Nil)

  def topoDfsSort(node: String, dfsStep: DfsStep, g: Graph[String]): DfsStep = {
    if (dfsStep.visited.contains(node)) dfsStep
    else {
      val preDfsStep = dfsStep.copy(visited = dfsStep.visited + node)
      val postDfsStep = g.neighbours(node)
        .foldLeft(preDfsStep)((step, n) => topoDfsSort(n, step, g))
      postDfsStep.copy(sort = node +: postDfsStep.sort)
    }
  }

}
